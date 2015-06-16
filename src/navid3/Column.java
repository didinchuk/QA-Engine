package navid3;




public class Column{
       String name;
       String dataType;
       int length;


       public Column(String n) {
              name = n;
       }

       public Column(String n, String dt) {
              name = n;
              dataType = dt;
       }
       
       public void setColumnType(String type) {
              dataType = type;
       }
       
       public void showColumn() {
    	   System.out.println("name = " + name + "dataType = " + dataType + "length = " + length );
		
	}
}
