package navid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DB2 {
	Data data;
	String schemaNameString;
	String tableNameString;
	
	public String createTableString(Data d) {
		StringBuilder createTableQueryStringBuilder = new StringBuilder(
				"CREATE TABLE ");
		System.out.println("Inside DB2 ");
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

					String temp = in.next().toLowerCase();
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
//		createTableQueryString = createTableQueryStringBuilder.toString();
		
		return createTableQueryStringBuilder.toString();
		
	}

}
