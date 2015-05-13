import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<SimpleRule> simpleRulesList = new ArrayList<SimpleRule>(5);

		// simpleRules = new SimpleRule();
		simpleRulesList.add(new SimpleRule("is", "25"));

		// simpleRules.get(0).operator = "is";
		// simpleRules.get(0).value = "25";

		// simpleRules[1] = new SimpleRule();

		simpleRulesList.add(new SimpleRule("=", "NOTNULL"));
		// simpleRulesList.get(1).operator = "=";
		// simpleRulesList.get(1).value = "NOTNULL";

		Properties prop = new Properties();
		prop.column = "email";
		prop.tableName = "users";

		RuleProcessor processor = new RuleProcessor();

		System.out.println("hi");

		System.out.println(processor.SimpleRuleToSQL(simpleRulesList, prop));

	}

}
