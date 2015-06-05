package navid;
//STEP 1. Import required packages
import java.sql.*;
import java.util.List;

import dataLoader.ReadCVS;

public class FirstExample {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "COM.ibm.db2.jdbc.app.DB2Driver";  
 static final String DB_URL = "jdbc:db2://cloud.clientspectrum.com:50000/CLOUD_DB";

 //  Database credentials
 static final String USER = "db2inst1";
 static final String PASS = "c5l@b123";
 
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 ReadCVS rCvs= new ReadCVS();
 try{
    //STEP 2: Register JDBC driver
    Class.forName(JDBC_DRIVER);

    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);

    //STEP 4: Execute a query
    System.out.println("Creating statement...");
    stmt = conn.createStatement();    

	Table t2 = new Table("C:/TempData/t.csv", "CAMPAIGNQA", "NAVID34");
	
//	String createQueryString = t2.getCreateTableQueryString();
//	
//    sql = "SELECT * FROM CAMPAIGNQA.QA_TEMPTABLES";
  //  sql = rCvs.createTableReturnString("C:/TempData/t.csv");
    //int i = stmt.executeUpdate(sql);
    
//	 stmt.executeUpdate("CREATE TABLE CAMPAIGNQA.NAVID5 (CustomerID int, FirstName varchar(4098), LastName varchar(4098), Email varchar(4098), DateOfBirth date)");
//	 stmt.executeUpdate(createQueryString);
	    
    stmt.executeUpdate("insert into CAMPAIGNQA.NAVID34 values (2, 'fd', 'sfd', 'sfd', '1988-03-03')");
    //STEP 5: Extract data from result set
//    while(rs.next()){
//       //Retrieve by column name
//       int id  = rs.getInt("id");
//       int age = rs.getInt("age");
//       String first = rs.getString("first");
    
//    System.out.println(rs.getString("SCREATESTATEMENT"));
//       String last = rs.getString("last");
//
//       //Display values
//       System.out.print("ID: " + id);
//       System.out.print(", Age: " + age);
//       System.out.print(", First: " + first);
//       System.out.println(", Last: " + last);
//    }
    //STEP 6: Clean-up environment
//    rs.close();
    stmt.close();
    conn.close();
 }catch(SQLException se){
    //Handle errors for JDBC
    se.printStackTrace();
 }catch(Exception e){
    //Handle errors for Class.forName
    e.printStackTrace();
 }finally{
    //finally block used to close resources
    try{
       if(stmt!=null)
          stmt.close();
    }catch(SQLException se2){
    }// nothing we can do
    try{
       if(conn!=null)
          conn.close();
    }catch(SQLException se){
       se.printStackTrace();
    }//end finally try
 }//end try
 System.out.println("Goodbye!");
}//end main
}//end FirstExample