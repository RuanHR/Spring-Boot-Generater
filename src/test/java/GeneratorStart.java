import java.util.ArrayList;
import java.util.List;

public class GeneratorStart {

	public static void main(String[] args) {
		List<String> tableNames = new ArrayList<>();
		// 配置生成表
		tableNames.add("user");

		// 调用生成方法
		CodeGenerator.genCode(tableNames);
	}

}
