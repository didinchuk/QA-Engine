import java.util.List;

public class RuleProcessor{
 
    public String SimpleRuleToSQL(List<SimpleRule> data, Properties properties){
     
        String query = "SELECT * FROM " + properties.tableName + " WHERE ";
     
        for(int i =0; i < data.size(); i++){
       
            if(i>0)
                query += " AND ";
           
            switch (data.get(i).type.toLowerCase()){
            case "length":
            	System.out.println("length");

                query +=  "LENGTH(" + properties.column + ") " + data.get(i).operator + ' '+ data.get(i).value;
            	break;

            case "null":
            	
            	System.out.println("=");

                query += properties.column +' '+ data.get(i).operator +' ' +  data.get(i).value ;
            	break;
            case "empty":
                query += properties.column +' '+ data.get(i).operator + ' ' + "'" + data.get(i).value + "'";
            	System.out.println("empty");
            	break;
            	
            case "value":
            	System.out.println("value");

                query += properties.column +' '+ data.get(i).operator +' ' + "'" + data.get(i).value + "'";
            	break;
            default:
            		System.out.println(data.get(i).type + " is not a valid data type!");
            	
            	
            }
       
        }
     
        return query + ";";
     
    }
 
    public static String AggregateRuleToSQL(AggregateRule data, Properties properties){
     
        String query = "SELECT ";
     
        query += data.aggregate + "(" + properties.column + ")" ;
        query += " FROM " + properties.tableName;
        
        
//        query += data.aggregate + "(" + properties.column + ")";
//        query += " FROM " + properties.tableName;
//        if (data.target == null)
//            query += " GROUP BY(" + data.target + ")";
     
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