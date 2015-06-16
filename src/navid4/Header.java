package navid4;

import java.util.ArrayList;
import java.util.HashSet;

public class Header {
       ArrayList<Column> columns;
       HashSet<String> hashSet ;
       
       public Header(){
              columns = new ArrayList<Column>();
              hashSet = new HashSet<String>();
       }
       
       public void addColumn(String name){
    	   columns.add(new Column(name));
    	   hashSet.add(name);
       }
       
       public boolean contains(String string) {
              
              return hashSet.contains(string);
       }
       
       public void print(){
    	   for(int i = 0; i < columns.size(); i++){
    		   System.out.println(columns.get(i).name);
    	   }
       }
}
