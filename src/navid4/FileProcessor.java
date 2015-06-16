package navid4;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileProcessor {
       String delimiterString;
       Data data;
       int rowLimit;
       int IgnoreLines;
       boolean hasHeader;

       public FileProcessor() {
              data = new Data();
       }

       public boolean getHasHeader() {
              return hasHeader;
       }

       public void setHasHeader(boolean hasHeader) {
              this.hasHeader = hasHeader;
       }

       public int getRowLimit() {
              return rowLimit;
       }

       public void setRowLimit(int rowLimit) {
              this.rowLimit = rowLimit;
       }

       public int getIgnoreLines() {
              return IgnoreLines;
       }

       public void setIgnoreLines(int IgnoreLines) {
              this.IgnoreLines = IgnoreLines;
       }

       public String getDelimiter() {
              return delimiterString;
       }

       public void setDelimiter(String delimetirString) {
              this.delimiterString = delimetirString;
       }


       public int getNumberOfColumns(String filePath) {

              String line = "";

              BufferedReader br = null;

              try {

                     br = new BufferedReader(new FileReader(filePath));

                     line = br.readLine();

                     String[] columns = line.split(delimiterString);
                     System.out.println("Numbe of columns isssssssssss "
                                  + columns.length + ".");
//                   data.setColumnCount(columns.length);

              } catch (FileNotFoundException e) {
                     e.printStackTrace();
              } catch (IOException e) {
                     e.printStackTrace();
              } finally {
                     if (br != null) {
                           try {
                                  br.close();
                           } catch (IOException e) {
                                  e.printStackTrace();
                           }
                     }
              }

              return data.header.columns.size();
       }
       
       public int countColumns(String filePath){
	   		try {
	   			BufferedReader br = new BufferedReader(new FileReader(filePath));
	   			String line = br.readLine();
	   			return line.split(delimiterString).length;
	   		}catch(FileNotFoundException e){
	   			
	   		} catch (IOException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
	   		return -1;
   		}
       
       private ArrayList<ArrayList<String>> readContent(BufferedReader br) throws IOException{
    	    String line;
    	    ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();
	        while ((line = br.readLine()) != null) {
	        	String[] temp = line.split(delimiterString);
	        	System.out.println(temp[0]);
	        	ArrayList<String> row = new ArrayList<String>();
	        	for(int i = 0; i < temp.length; i++){
	        		row.add(temp[i]);
	        	}	        	
	        	content.add(row);
	        }
	    	return content;   
       }

       public Data read(String filePath){
    	   System.out.println("Has headers");
    	   data = new Data();
           try {
    	   		BufferedReader br = new BufferedReader(new FileReader(filePath));
    	   		String[] row =  br.readLine().split(delimiterString);
    	   		ArrayList<String> headers = new ArrayList<String>();
    	   		for(int i = 0; i < row.length; i++)
    	   			headers.add(i, row[i]);
    	   		data.addHeaderData(headers);
    	   		data.allRowsLists = readContent(br);
    	   		data.showAllRows();
    	   		data.header.print();

           } catch (FileNotFoundException e) {
                  e.printStackTrace();
           } catch (IOException e) {
                  e.printStackTrace();           
           }

           return data;
       }
       
       public Data read(String filePath, ArrayList<String> headers ){
    	   System.out.println("No headers");
    	   data = new Data();
           try {
        	   data.addHeaderData(headers);
        	   BufferedReader br = new BufferedReader(new FileReader(filePath));
    	   		data.allRowsLists = readContent(br);
    	   		

           } catch (FileNotFoundException e) {
                  e.printStackTrace();
           } catch (IOException e) {
                  e.printStackTrace();           
           }

           return data;
       }
       // //////////////////

       public Data make2DData5(String filePath) {
              
              Data d = new Data();

//            data.setColumnCount(getNumberOfColumns(filePath));

              String line = "";
              String tempInputString = " ";

              // typeOfColumns = new ArrayList<String>();
              BufferedReader br = null;
              Scanner in = new Scanner(System.in);

              // String delimiterString = ",";

              int i = 0;
              try {

                     br = new BufferedReader(new FileReader("C:/TempData/t.csv"));
                     line = br.readLine();

                     String[] columns = line.split(delimiterString);
                     System.out.println("Numbe of columns is " + columns.length + ".");
                     for (; i < columns.length; ++i) {

                           while (tempInputString != "i" && tempInputString != "d"
                                         && tempInputString != "v") {

                                  System.out
                                                .println("Please type the type of column #"
                                                              + (i + 1) + " whose name is \""
                                                              + columns[i] + "\"");
                                  System.out
                                                .println("Please enter \'i\' for integer, \'d\' for Date and \'v\' for Varchar(255):");

                                  String temp = in.next().toLowerCase();
                                  tempInputString = temp;
                                  System.out.println("input is: " + tempInputString);
                                  switch (tempInputString) {
                                  case "i":
                                         data.header.columns.get(i).dataType = "int";
                                         break;

                                  case "d":
                                         data.header.columns.get(i).dataType = "date";
                                         break;

                                  case "v":
                                         data.header.columns.get(i).dataType = "varchar(255)";
                                         break;


                                  default:
                                         System.out.println("Your input is not valid.");
                                         break;
                                  }

                           }

                           tempInputString = " ";

                     }
                     
                     in.close();

              } catch (FileNotFoundException e) {
                     e.printStackTrace();
              } catch (IOException e) {
                     e.printStackTrace();
              } finally {
                     if (br != null) {
                           try {
                                  br.close();
                           } catch (IOException e) {
                                  e.printStackTrace();
                           }
                     }
              }
              
              data = d;

              return data;
       }


       
       public Data setHeaderColumnsByFR(int columnCount) {
              // TODO Auto-generated method stub
              Data d = new Data();
              
              
              data = d;
              return d;
              
              
       }



}
