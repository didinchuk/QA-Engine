package navid;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;

public class Data {

	int columnCount;
	List<String> columnHeaderList;

	HashMap<String, String> typeOfColumns;

	// each ArrayList inside the List is one row.
	/*
	Maybe use an ArrayList<ArrayList<String>> for fastest key retrieval - ANDREW DIDINCHUK
	*/
	List<ArrayList<String>> allRowsLists;

	public Data() {
		typeOfColumns = new HashMap<String, String>();
		columnHeaderList = new ArrayList<String>();
		allRowsLists = new ArrayList<ArrayList<String>>();

	}

	public int getColumnCount() {
		System.out.println("getColumnCount inside data: column Count is "+columnCount);
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public HashMap<String, String> getTypeOfColumns() {
		return typeOfColumns;
	}

	public void setTypeOfColumns(HashMap<String, String> typeOfColumns) {
		this.typeOfColumns = typeOfColumns;
	}

	public List<ArrayList<String>> getRowColumnLists() {
		return allRowsLists;
	}

	public List<String> getColumnHeaderList() {
		return columnHeaderList;
	}

	public void setColumnHeaderList(List<String> columnList) {
		this.columnHeaderList = columnList;
	}

	public void setRowColumnLists(List<ArrayList<String>> rowColumnLists) {
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
	
	public void showTypes () {
		System.out.println("Here are types that you typed which are saved in a hashMap:");
		for (String s: typeOfColumns.keySet()){
			System.out.println("key = " + s + " and value = "+ typeOfColumns.get(s));
		}
		
	}

}
