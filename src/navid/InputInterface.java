package navid;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ibm.db2.jcc.am.i;

public class InputInterface {
	FileReaderClass fr;
	Data data;
	boolean hasHeader;
	ArrayList<String> columnsHeaderList;
	String filePath;
	int columnCount;

	public InputInterface() {
		columnsHeaderList = new ArrayList<String>();
		fr = new FileReaderClass();
		data = new Data();
	}

	public void setFilePath(String fp) {

		filePath = fp;
	}

	public String getFilePath() {
		
		if(filePath != null)
			return filePath;
		
//		System.out.println("Would you like to use the default path?");
//		System.out.println("press 'y' to accept the default path and any other key for defining your own path.");
//
//		Scanner in = new Scanner(System.in);
//		String tempPathChoice= in.next();
//		if(tempPathChoice.charAt(0)=='y' || tempPathChoice.charAt(0)=='Y'){
			setFilePath("C:/TempData/t.csv");
//		}
//		else {
//
//			System.out.println("Please enter the path like:\n"
//					+ "C:/tables/table.csv");
//			setFilePath(in.next());
//			
//		}
//		in.close();
		return filePath;
	}

	public void setHasHeaders(boolean h) {
		hasHeader = h;
	}

	public void setHeaderColumnsByUser(int columnCount) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < columnCount; i++) {
			System.out.println("Please enter the column name for column#"+(i + 1));
			columnsHeaderList.add(in.nextLine());
			
		}
		

	}

	public boolean getHeaders() {
		return hasHeader;
	}

	public void getDataTypes(ArrayList<Column> columnList) {
		Scanner in = new Scanner(System.in);
		System.out.println("number of columns is: "+ columnList.size());
		List<String> tempColumnTypeList = new ArrayList<String>();
		String tempString;
		for (int i = 0; i < columnList.size(); i++) {
			
			System.out.println("Please enter the column type for column#"+(i + 1) + " whose name is: \""+ data.header.columns.get(i)+"\"");
			tempString = in.next();
//			tempColumnTypeList.add(tempString);
			data.header.columns.get(i).dataType = tempString;
			
			
			
//			data.typeOfColumns.put(data.header.columns.get(i), tempString);
//			tempColumnTypeList.add(in.nextLine());
				
		}
		
		
		
	}
	
	public void getDataTypes2(String filePathString ) {
		
	}

	public void setColumnHeaderListByUser(int numberOfColumns, Data d) {
		
		
	}
	public void run() {

		getFilePath();
		setHasHeaders(false);
		
		fr.setDelimiter(",");
		fr.setRowLimit(100);
		fr.setIgnoreLines(0);
		fr.setHasHeader(this.hasHeader);
		columnCount = fr.getNumberOfColumnFromHeader(filePath);
		
		data = fr.make2DData5(filePath);
		
		if (!hasHeader) {
			setHeaderColumnsByUser(columnCount);
		}
//		else{
//			setHeaderColumnsByUser(data.header.columns.size());
//			data.setColumnHeaderListByUser(columnCount);
//		}
//		
//
////		data = fr.make2DData5(this.filePath);
//		System.out.println();
//		System.out.println("Here are all the rows:");
//		data.showAllRows();
//		getDataTypes(data.header.columns);
////		data.showTypes();
//		DB2 db2 = new DB2();
////		db2.createTableString(data);
//		
//		
		
		
	}

	public static void main(String[] args) {
		InputInterface ii = new InputInterface();
		ii.run();
	}

}
