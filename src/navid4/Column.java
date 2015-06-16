package navid4;

import java.util.ArrayList;

public class Column{
       String name;
       String dataType;
       int length;
//     String fieldType;

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
}
