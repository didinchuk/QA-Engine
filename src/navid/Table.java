package navid;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Table {

	String schemaNameString;
	String tableNameString;
	String pathString;
	String createTableQueryString;

	List<String> insertQueryStrings;

	public Table(String path, String s, String t) {
		pathString = path;
		schemaNameString = s;
		tableNameString = t;

	}

	public Table(String s, String t) {

		schemaNameString = s;
		tableNameString = t;

	}

	// ADD GETTER AND SETTER METHODS ^^
	public Table() {

	}

	public static void main(String[] args) {

		Table t3 = new Table("CAMPAIGNQA", "NAVID34");
		t3.getCreateTableQueryString();

	}

	public List<String> getInsertQueryStrings() {
		if (insertQueryStrings == null) {
			getInsertQueryStrings();
		}
		return insertQueryStrings;
	}

	public void makeInsertQueryStrings(String pathString) {
		// IT IS RECOMMENDED TO BUILD THE CREATE QUERY IN THIS METHOD.

		// String pathString =
		// transformSlashtoBackslash("C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv");
		// String pathString =
		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		if (tableNameString == null) {

		}
		List<String> sqlQueryInsertList = new ArrayList<String>();
		StringBuilder sqlQueryInsertStringBuilder = new StringBuilder();

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[] columnNames = null;

		try {

			br = new BufferedReader(new FileReader(pathString));
			int counter = 0;
			while ((line = br.readLine()) != null) {
				if (counter == 0) {

					// sqlQueryInsertStringBuilder.append("INSERT INTO ");
					columnNames = line.split(cvsSplitBy);
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
					String[] customerStrings = line.split(cvsSplitBy);
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
	}

	public String getCreateTableQueryString() {

		if (createTableQueryString == null)
			makeCreateTableQuery();

		return createTableQueryString;

	}

	public void makeCreateTableQuery() {

		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		// if (pathString == null) {
		// pathString = "C:/TempData/t.csv";
		// }
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

		// typeOfColumns = new ArrayList<String>();
		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ",";

		int i = 0;
		try {

			br = new BufferedReader(new FileReader("C:/TempData/t.csv"));
			line = br.readLine();

			String[] columns = line.split(cvsSplitBy);
			System.out.println("Numbe of columns is " + columns.length + ".");
			for (; i < columns.length; ++i) {

				createTableQueryStringBuilder.append(columns[i] + ' ');

				while (tempInputString != 'i' && tempInputString != 'd'
						&& tempInputString != 'v') {

					System.out
							.println("Please type the type of column #"
									+ (i + 1) + " whose name is \""
									+ columns[i] + "\"");
					System.out
							.println("Please enter \'i\' for integer, \'d\' for Date and \'v\' for Varchar(4098):");

					String temp = in.next();
					tempInputString = temp.charAt(0);
					System.out.println("input is: " + tempInputString);
					switch (tempInputString) {
					case 'i':
						createTableQueryStringBuilder.append("int, ");
						break;

					case 'd':
						createTableQueryStringBuilder.append("date, ");
						break;

					case 'v':
						createTableQueryStringBuilder.append("varchar(4098), ");
						break;

					default:
						System.out.println("Your input is not valid.");
						break;
					}

				}

				tempInputString = ' ';

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
		createTableQueryStringBuilder.setLength(createTableQueryStringBuilder
				.length() - 2);
		createTableQueryStringBuilder.append(")");
		System.out.println("The \"Create Table\" SQL Query is:");
		System.out.println(createTableQueryStringBuilder);
		createTableQueryString = createTableQueryStringBuilder.toString();
	}

}
