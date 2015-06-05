package navid;

import java.io.FileReader;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderClass {
	String delimiterString;
	Data data;
	int rowLimit;
	int IgnoreLines;
	boolean hasHeader;
	
	public FileReaderClass() {
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

	public Data make2DData(String filePath) {
		data = new Data();
		if (hasHeader) {
			return make2DDataWithHeader(filePath);
		} else {

			// return make2DDataWithoutHeader(filePath);
		}
		return data;

	}

	public Data make2DData3(String filePath) {
		

		data.setColumnCount(getNumberOfColumns(filePath));

		String line = "";

		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(filePath));
	
			String[] columns;
			int countLineNumber = 0;

			if (hasHeader) {

				line = br.readLine();

				columns = line.split(delimiterString);
				System.out.println("Numbe of columns is " + columns.length + ".");
				data.setColumnCount(columns.length);
				for (int i = 0; i < columns.length; i++) {
					System.out.println("column#" + i + " is "+columns[i]);
					data.columnHeaderList.add(columns[i]);
					
				}
			}
			while ((line = br.readLine() )!= null) {
				ArrayList<String> eachRowArrayList = new ArrayList<String>();
				columns = line.split(delimiterString);
				for (String s: columns) {
					eachRowArrayList.add(s);
					
				}
				
				for (int i = 0; i < columns.length; i++) {
					System.out.println("columns#" + i +" is " + columns[i]);
				}

				System.out.println("row#" + countLineNumber + " name is: " + columns[1]);
				data.allRowsLists.add(eachRowArrayList);
				++countLineNumber;
				
			}
			
//			System.out.println("3: number of rows is: "+ columns.length);
			
		

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
	public Data make2DDataWithHeader(String filePath) {

		
		getNumberOfColumns(filePath);

		String line = "";

		BufferedReader br = null;

		try {

			// maybe add a getColumnCount
			br = new BufferedReader(new FileReader(filePath));

			line = br.readLine();

			String[] columns = line.split(delimiterString);
			System.out.println("Numbe of columns is " + columns.length + ".");
			data.columnHeaderList= new ArrayList<String>();
			data.setColumnCount(columns.length);
			for (int i = 0; i < columns.length; i++) {
				System.out.println("row" + i + " is "+columns[i]);
				
			}
			
			for (String string : columns) {
				data.columnHeaderList.add(string);
				System.out.println("String " + string + " is added.");
				
			}

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
	
	public int getNumberOfColumns(String filePath){

		String line = "";

		
		BufferedReader br = null;

		try {

			br = new BufferedReader(new FileReader(filePath));

			line = br.readLine();

			String[] columns = line.split(delimiterString);
			System.out.println("Numbe of columns isssssssssss " + columns.length + ".");
			data.setColumnCount(columns.length);
			

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

		return data.columnCount;
	}
	
	////////////////////
	
	public Data make2DData2(String pathString) {
		// IT IS RECOMMENDED TO BUILD THE CREATE QUERY IN THIS METHOD.

		// String pathString =
		// transformSlashtoBackslash("C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv");
		// String pathString =
		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";


		List<String> sqlQueryInsertList = new ArrayList<String>();
//		data.rowColumnLists = new List<List<String>>() ;
		StringBuilder sqlQueryInsertStringBuilder = new StringBuilder();

		BufferedReader br = null;
		String line = "";
//		String cvsSplitBy = ",";
		String[] columnNames = null;

		try {

			br = new BufferedReader(new FileReader(pathString));
			int counter = 0;
			while ((line = br.readLine()) != null) {
				if (hasHeader) {

					// sqlQueryInsertStringBuilder.append("INSERT INTO ");
					columnNames = line.split(delimiterString);
					System.out.println("Customer [id = " + columnNames[0]
							+ " , First Name = " + columnNames[1] + "]");
					sqlQueryInsertList.add(sqlQueryInsertStringBuilder
							.toString());
					String CustomerID = columnNames[0];
					System.out.println("first line");

				} else {

					sqlQueryInsertStringBuilder.setLength(0);
					sqlQueryInsertStringBuilder.append("INSERT INTO ");
					sqlQueryInsertStringBuilder.append(columnNames[1]);
					String[] customerStrings = line.split(delimiterString);
					System.out.println("Customer [id = " + customerStrings[0]
							+ " , First Name = " + customerStrings[1] + "]");
					sqlQueryInsertList.add(sqlQueryInsertStringBuilder
							.toString());
				}
				++counter;
			}

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

		System.out.println("Done");
		System.out.println("This is the Insert SQL query:");
		System.out.println(sqlQueryInsertList);
		
		return data;
	}

	
		
}
