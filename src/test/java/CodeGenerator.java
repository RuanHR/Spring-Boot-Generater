
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.GeneratedKey;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.ModelType;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author zhangchong
 * @since 2017年7月6日下午3:56:10
 * @description 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发
 */
public class CodeGenerator {

	public static void genCode(List<String> tableNames) {
		for (String tableName : tableNames) {
			genModelAndMapper(tableName);
		}
	}

	public static void genModelAndMapper(String tableName) {
		Context context = new Context(ModelType.FLAT);
		context.setId("MyBatis");
		context.setTargetRuntime("MyBatis3Simple");
		context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
		context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

		JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
		jdbcConnectionConfiguration.setConnectionURL(ProjectConstant.JDBC_URL);
		jdbcConnectionConfiguration.setUserId(ProjectConstant.JDBC_USERNAME);
		jdbcConnectionConfiguration.setPassword(ProjectConstant.JDBC_PASSWORD);
		jdbcConnectionConfiguration.setDriverClass(ProjectConstant.JDBC_DIVER_CLASS_NAME);
		context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

		PluginConfiguration pluginConfiguration = new PluginConfiguration();
		pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
		pluginConfiguration.addProperty("mappers", ProjectConstant.MAPPER_INTERFACE_REFERENCE);
		// 增加自定义注释
		pluginConfiguration.addProperty("commentCfg", "CustomCommentGenerator");
		context.addPluginConfiguration(pluginConfiguration);

		// 取消默认getter setter
		PluginConfiguration CustomPluginConfiguration = new PluginConfiguration();
		CustomPluginConfiguration.setConfigurationType("CustomPluginAdaptor");
		context.addPluginConfiguration(CustomPluginConfiguration);

		JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
		javaModelGeneratorConfiguration.setTargetProject(ProjectConstant.JAVA_PATH);
		javaModelGeneratorConfiguration.setTargetPackage(ProjectConstant.MODEL_PACKAGE);
		javaModelGeneratorConfiguration.addProperty("rootClass", ProjectConstant.DOMAIN_EXTENDS_REFERENCE);
		context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

		SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
		sqlMapGeneratorConfiguration.setTargetProject(ProjectConstant.RESOURCES_PATH);
		sqlMapGeneratorConfiguration.setTargetPackage("mapper");
		context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

		JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
		javaClientGeneratorConfiguration.setTargetProject(ProjectConstant.JAVA_PATH);
		javaClientGeneratorConfiguration.setTargetPackage(ProjectConstant.MAPPER_PACKAGE);
		javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
		context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

		TableConfiguration tableConfiguration = new TableConfiguration(context);
		tableConfiguration.setTableName(tableName);
		tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));
		context.addTableConfiguration(tableConfiguration);

		List<String> warnings;
		MyBatisGenerator generator;
		try {
			Configuration config = new Configuration();
			config.addContext(context);
			config.validate();

			boolean overwrite = true;
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			warnings = new ArrayList<String>();
			generator = new MyBatisGenerator(config, callback, warnings);
			generator.generate(null);
		} catch (Exception e) {
			throw new RuntimeException("生成Model和Mapper失败", e);
		}

		if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
			throw new RuntimeException("生成Model和Mapper失败：" + warnings);
		}

		String modelName = tableNameConvertUpperCamel(tableName);
		System.out.println(modelName + ".java 生成成功");
		System.out.println(modelName + "Mapper.java 生成成功");
		System.out.println(modelName + "Mapper.xml 生成成功");
	}

	private static String tableNameConvertLowerCamel(String tableName) {
		StringBuilder result = new StringBuilder();
		if (tableName != null && tableName.length() > 0) {
			tableName = tableName.toLowerCase();// 兼容使用大写的表名
			boolean flag = false;
			for (int i = 0; i < tableName.length(); i++) {
				char ch = tableName.charAt(i);
				if ("_".charAt(0) == ch) {
					flag = true;
				} else {
					if (flag) {
						result.append(Character.toUpperCase(ch));
						flag = false;
					} else {
						result.append(ch);
					}
				}
			}
		}
		return result.toString();
	}

	private static String tableNameConvertUpperCamel(String tableName) {
		String camel = tableNameConvertLowerCamel(tableName);
		return camel.substring(0, 1).toUpperCase() + camel.substring(1);

	}
}
