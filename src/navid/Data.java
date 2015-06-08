package navid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Data {

//	int columnCount;
	Header header ;
	//ArrayList<Column> columnObjectList;
//	HashMap<String, String> typeOfColumns;

	// each ArrayList inside the List is one row.
	/*
	Maybe use an ArrayList<ArrayList<String>> for fastest key retrieval - ANDREW DIDINCHUK
	*/
	ArrayList<ArrayList<String>> allRowsLists;

	public Data() {
//		typeOfColumns = new HashMap<String, String>();
		header = new Header();
		
//		allRowsLists = new ArrayList<ArrayList<String>>();
		
	}
	
	
	

//	public HashMap<String, String> getTypeOfColumns() {
//		return typeOfColumns;
//	}
//
//	public void setTypeOfColumns(HashMap<String, String> typeOfColumns) {
//		this.typeOfColumns = typeOfColumns;
//	}

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

	public void showAllRows() {
		for(ArrayList<String>aList : allRowsLists){
			for(String s: aList){
				System.out.print(s +" ");
			}
			System.out.println();
		}
	}
	
//	public void showTypes () {
//		System.out.println("Here are types that you typed which are saved in a hashMap:");
//		for (String s: typeOfColumns.keySet()){
//			System.out.println("key = " + s + " and value = "+ typeOfColumns.get(s));
//		}
//		
//	}

}
