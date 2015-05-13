import java.util.List;

public class RuleProcessor{
 
    public String SimpleRuleToSQL(List<SimpleRule> data, Properties properties){
     
        String query = "SELECT * FROM " + properties.tableName + " WHERE ";
     
        for(int i =0; i < data.size(); i++){
       
            if(i>0)
                query += " AND ";
            query += properties.column + data.get(i).operator + data.get(i).value;
       
        }
     
        return query + ";";
     
    }
 
    public static String AggregateRuleToSQL(AggregateRule data, Properties properties){
     
        String query = "SELECT ";
     
        query += data.aggregate + "(" + properties.column + ")";
        query += " FROM " + properties.tableName;
        if (data.target == null)
            query += " GROUP BY(" + data.target + ")";
     
        return query + ";";
     
    }
 
    public String JoinRule(JoinRule data, Properties properties){
    
        String query = "SELECT * FROM " + properties.tableName;
     
        query += " INNER JOIN " + data.table + " ON ";
        query += properties.tableName + "." + properties.column + "=";
        query += data.table + "." + data.field;
     
        return query + ";";
    }
 
}