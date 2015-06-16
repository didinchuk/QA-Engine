package navid4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//import fileprocessor.Data;
//import fileprocessor.FileProcessor;

public class InterfaceTest {

	FileProcessor fr;
	Data data;
	boolean hasHeader;
	ArrayList<String> columnsHeaderList;
	String filePath;
	String delimiter;
	int rowLimit;
	int ignoreLines;
	int columnCount;

	public InterfaceTest() {
		columnsHeaderList = new ArrayList<String>();
		fr = new FileProcessor();
		data = new Data();
	}

	public void getFilePath() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the path like:\n"
				+ "C:/tables/CampaignQA_BradTest.dat");
		this.filePath = in.next();
	}

	public void askHasHeaders() {
		Scanner in = new Scanner(System.in);
		System.out.println("Does your file have headers? (y/n):");
		String temp = in.next();
		if (temp == "y" || temp == "Y") {
			this.hasHeader = true;
		} else if (temp == "n" || temp == "N") {
			this.hasHeader = false;
		} else {
			this.hasHeader = false;
		}
	}

	public void askDelimiter() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a delimiter to use:");
		this.delimiter = in.next();
	}

	public void askRowLimit() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a row limit");
		this.rowLimit = Integer.parseInt(in.next());
	}

	public void askIgnoreLines() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter lines to ignore");
		this.ignoreLines = Integer.parseInt(in.next());
	}

	public void askHeaderNames(int columnCount) {
		Scanner in = new Scanner(System.in);
		for (int i = 0; i < columnCount; i++) {
			System.out.println("Please enter the column name for column#"
					+ (i + 1));
			columnsHeaderList.add(in.nextLine());

		}

	}

	public boolean getHeaders() {
		return hasHeader;
	}

	/*
	 * public void getDataTypes(List<String> columnList) { Scanner in = new
	 * Scanner(System.in); System.out.println("number of columns is: "+
	 * columnList.size()); List<String> tempColumnTypeList = new
	 * ArrayList<String>(); String tempString; for (int i = 0; i <
	 * columnList.size(); i++) {
	 * 
	 * System.out.println("Please enter the column type for column#"+(i + 1) +
	 * " whose name is: \""+ data.columnHeaderList.get(i)+"\""); tempString =
	 * in.next(); // tempColumnTypeList.add(tempString);
	 * 
	 * data.typeOfColumns.put(data.columnHeaderList.get(i), tempString); //
	 * tempColumnTypeList.add(in.nextLine());
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 */

	public void run() {

		intro();
		/*
		 * getFilePath(); askHasHeaders(); askDelimiter(); askRowLimit();
		 * askIgnoreLines();
		 */
		/*
		 * fr.setDelimiter(this.delimiter); fr.setRowLimit(this.rowLimit);
		 * fr.setIgnoreLines(this.ignoreLines); fr.setHasHeader(this.hasHeader);
		 */

		this.filePath = "C:/TempData/t.csv";
		this.hasHeader = true;
		fr.setDelimiter(",");
		fr.setRowLimit(1);
		fr.setIgnoreLines(0);
		fr.setHasHeader(this.hasHeader);

		columnCount = fr.countColumns(this.filePath);
		if (!this.hasHeader) {
			askHeaderNames(columnCount);
			data = fr.read(filePath, this.columnsHeaderList);
			// fr.headers = columnsHeaderList;
		} else {
			data = fr.read(filePath);
		}
//		getTypesFromUser();
		getTypesByDefault();

		data.showHeaders();

		getTableName();
//		setDefaultTableName();
		
		data.showAllRows();
		DB2 db2 = new DB2();

		String createTableTempString = db2.createTableString(data);
		db2.exectueCreateTable(createTableTempString);
		db2.update_QA_TEMPTABLES(createTableTempString,data.tableNameString);
		db2.makeInsertDataQuery(data);
		
//		ArrayList<String> insertList = db2.makeInsertDataQueryWithMaxBatchLimit(data);
//
//		System.out.println("insert queries:");
//		for(String string : insertList){
//			System.out.println(string);
//		}
//		System.out.println();
		db2.exectueInsertData(db2.makeInsertDataQueryWithMaxBatchLimit(data));

	}

	public void getTableName() {
		Scanner in = new Scanner(System.in);
		System.out.println("Please insert the table name:");
		data.tableNameString = in.next().toUpperCase();
	}

	public void getTableName2() {

		Scanner in2 = new Scanner(System.in);

		String string = in2.next();
		System.out.println("your input is " + string);
		// in2.close();

	}

	public void getTypesFromUser2() {
		int count = 1;
		Scanner in = new Scanner(System.in);
		for (Column c : data.header.columns) {
			System.out.println("Please enter the type for column #" + count
					+ " whose name is \"" + c.name + "\"");
			// String tempTyepString =
			c.dataType = in.next();
		}

	}

	public void getTypesFromUser() {
		int i = 0;

		Scanner in = new Scanner(System.in);

		String tempInputString = " ";
		System.out.println("Numbe of columns is " + data.header.columns.size()
				+ ".");
		for (; i < data.header.columns.size(); ++i) {

			while (!tempInputString.equals("i") && !tempInputString.equals("s") && !tempInputString.equals("v")
					&& !tempInputString.equals("d")) {

				System.out.println("Please type the type of column #" + (i + 1)
						+ " whose name is \"" + data.header.columns.get(i).name
						+ "\"");
				System.out.println("Please enter \'i\' for integer, \'s\' for small integer,"
						+ " \'d\' for Date and \'v\' for Varchar(255):");

				String temp = in.next().toLowerCase();
				tempInputString = temp;
				System.out.println("input is: " + tempInputString);
				switch (tempInputString) {
				case "i":
					data.header.columns.get(i).dataType = "INT";
					break;

				case "s":
					data.header.columns.get(i).dataType = "SMALLINT";
					break;

				case "d":
					data.header.columns.get(i).dataType = "DATE";
					break;

				case "v":
					data.header.columns.get(i).dataType = "VARCHAR(255)";
					break;

				default:
					System.out.println("Your input is not valid.");
					break;
				}

			}

			tempInputString = " ";

		}

	}
	public void setDefaultTableName() {
		data.tableNameString = "navidTest";
	}
	public void getTypesByDefault() {

		System.out.println("ALL TYPES HAS ASSIGNED AS INT BY DEFAULT.");


		data.header.columns.get(0).dataType = "INT";
		for (int j = 1; j < 4; j++) {

			data.header.columns.get(j).dataType = "VARCHAR(255)";
		}

		data.header.columns.get(4).dataType = "DATE";

	}

	public void intro() {
		System.out.println("Welcome");
	}

	public static void main(String[] args) {
		InterfaceTest ii = new InterfaceTest();
		ii.run();
	}

}
