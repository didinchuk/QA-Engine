package navid3;

import com.ibm.db2.jcc.am.ne;

public class DB2 {
	
	public String createTableString(Data d) {
		StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
		
		stringBuilder.append(Common.schemaNameString);
		stringBuilder.append(".");
		stringBuilder.append(d.tableNameString);

		stringBuilder.append(" (");
		
		
		
		
		
		return stringBuilder.toString();
		
	}

}
 