import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<SimpleRule> simpleRulesList = new ArrayList<SimpleRule>(5);
		List<SimpleRule> aggregateRulesList = new ArrayList<SimpleRule>();

		// simpleRules = new SimpleRule();
//		simpleRulesList.add(new SimpleRule( "AVG", "is", "25"));

		// simpleRules.get(0).operator = "is";
		// simpleRules.get(0).value = "25";

		// simpleRules[1] = new SimpleRule();

//		simpleRulesList.add(new SimpleRule("value","=", "navid@navidcs.com"));
		simpleRulesList.add(new SimpleRule("LENGTH", ">",  "5"));
//		simpleRulesList.add(new SimpleRule("EMPTY", "!=",  ""));
//		simpleRulesList.add(new SimpleRule("NULL", "IS",  "NULL"));
		// simpleRulesList.get(1).operator = "=";
		// simpleRulesList.get(1).value = "NOTNULL";

		Properties prop = new Properties();
		prop.column = "email";
		prop.tableName = "usersTable";

		RuleProcessor processor = new RuleProcessor();


		System.out.println(processor.SimpleRuleToSQL(simpleRulesList, prop));
//		System.out.println(processor.AggregateRuleToSQL(simpleRulesList, prop));

	}

}
