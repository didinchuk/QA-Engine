package navid;



import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileReader2 {
	String delimiterString;
	Data data;
	int rowLimit;
	int IgnoreLines;
	boolean hasHeader;
	
	
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
	
	public Data make2DData(String filePath) {
		data = new Data();
		if (hasHeader) {
//			return make2DDataWithHeader(filePath);
		}
		else {

//			return make2DDataWithoutHeader(filePath);
		}
		return data;
		
	}
	/*
	
	public Data make2DDataWithHeader(String filePath) {
		
		String line = "";

		String schemaNameString = null;
		String tableNameString = null;
		StringBuilder createTableQueryStringBuilder = new StringBuilder(
				"CREATE TABLE ");
		Scanner in = new Scanner(System.in);
		if (schemaNameString == null) {
			System.out.println("Please enter the schema name:");
			schemaNameString = in.next();
			// ^^^^^
		}
		createTableQueryStringBuilder.append(schemaNameString + ".");

		if (tableNameString == null) {
			System.out.println("Please enter the table name:");
			tableNameString = in.next();
			// ^^^^^
		}
		createTableQueryStringBuilder.append(tableNameString + " (");

		char tempInputString = ' ';


		BufferedReader br = null;

		int i = 0;
		try {


			br = new BufferedReader(new FileReader("C:/TempData/t.csv"));

//			br = new BufferedReader(new FileReader("C:/TempData/t.csv"));
			
//			line = br.readLine();
//
//			String[] columns = line.split(cvsSplitBy);
//			System.out.println("Numbe of columns is " + columns.length + ".");
//			for (; i < columns.length; ++i) {
//
//				createTableQueryStringBuilder.append(columns[i] + ' ');
//
//				while (tempInputString != 'i' && tempInputString != 'd'
//						&& tempInputString != 'v') {
//
//					System.out
//							.println("Please type the type of column #"
//									+ (i + 1) + " whose name is \""
//									+ columns[i] + "\"");
//					System.out
//							.println("Please enter \'i\' for integer, \'d\' for Date and \'v\' for Varchar(4098):");
//
//					String temp = in.next();
//					tempInputString = temp.charAt(0);
//					System.out.println("input is: " + tempInputString);
//					switch (tempInputString) {
//					case 'i':
//						createTableQueryStringBuilder.append("int, ");
//						break;
//
//					case 'd':
//						createTableQueryStringBuilder.append("date, ");
//						break;
//
//					case 'v':
//						createTableQueryStringBuilder.append("varchar(4098), ");
//						break;
//
//					default:
//						System.out.println("Your input is not valid.");
//						break;
//					}
//
//				}
//
//				tempInputString = ' ';
//
//			}
//
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

		
		
		return data;
	}
	
	
*/	


}
