package navid4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Data {

       Header header ;
       String tableNameString;
       String schemaNameString = "CAMPAIGNQA";
  
       ArrayList<ArrayList<String>> allRowsLists;

       public Data() {

              header = new Header();
              
              allRowsLists = new ArrayList<ArrayList<String>>();
              
       }
       
       public void addHeaderData(ArrayList<String> headers){
    	   for(int i =0; i < headers.size(); i++){
    		   this.header.addColumn(headers.get(i));
    	   }
       }
       
       
       
       public ArrayList<ArrayList<String>> getRowColumnLists() {
              return allRowsLists;
       }

       public ArrayList<Column> getColumnHeaderList() {
              return header.columns;
       }

       public void setColumnHeaderListByUser(int numberOfColumns) {
              Scanner in = new Scanner(System.in);
              for (int i = 0; i < numberOfColumns; i++) {
                     System.out.println("Please enter the name of column#"+i);
                     header.columns.add(new Column(in.next()));
                     
              }
       }

       public void setRowColumnLists(ArrayList<ArrayList<String>> rowColumnLists) {
              this.allRowsLists = rowColumnLists;
       }

       

       public void showHeaders() {
    	   System.out.println("Here are the Header names:");
             
                     for(Column c:  header.columns){
                           System.out.println( "name = " + c.name + " type = " + c.dataType + " ");
                     }
                     System.out.println();
                     System.out.println();
              
       }
       

       public void showAllRows() {
    	   System.out.println("Here are the rows (Note that you shouldn't see the headers!)");
              for(ArrayList<String>aList : allRowsLists){
                     for(String s: aList){
                           System.out.print(s +" ");
                     }
                     System.out.println();
              }
              System.out.println();
       }
       
//     public void showTypes () {
//            System.out.println("Here are types that you typed which are saved in a hashMap:");
//            for (String s: typeOfColumns.keySet()){
//                   System.out.println("key = " + s + " and value = "+ typeOfColumns.get(s));
//            }
//            
//     }

}
