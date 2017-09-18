import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ProjectConstant
 * @date 2017年9月18日 下午1:09:22
 * @author ToniR
 * @Description: 项目常量
 */
public final class ProjectConstant {

	// JDBC配置，请修改为你项目的实际配置
	public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/spring-boot";
	public static final String JDBC_USERNAME = "root";
	public static final String JDBC_PASSWORD = "root";
	public static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	// 项目在硬盘上的基础路径
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	// java文件路径
	public static final String JAVA_PATH = PROJECT_PATH + "/src/main/java";
	// 资源文件路径
	public static final String RESOURCES_PATH = PROJECT_PATH + "/src/main/resources";

	// 项目基础包名称，根据项目修改
	public static final String BASE_PACKAGE = "com.pactera";
	// 实体所在包
	public static final String MODEL_PACKAGE = BASE_PACKAGE + ".business.domain";
	// Mapper所在包
	public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".business.mapper";
	// Mapper插件基础接口的完全限定名
	public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".base.core.Mapper";
	// BaseDomain继承方法
	public static final String DOMAIN_EXTENDS_REFERENCE = BASE_PACKAGE + ".base.domain.BaseDomain";

	public static void main(String[] args) {
		List<String> tableNames = new ArrayList<>();
		// 配置生成表
		tableNames.add("user");

		// 调用生成方法
		CodeGenerator.genCode(tableNames);
	}

}
