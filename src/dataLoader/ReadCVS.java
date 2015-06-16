package dataLoader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.css.Counter;

public class ReadCVS {

	public static void main(String[] args) {

		ReadCVS obj = new ReadCVS();
		// obj.showRows();
		// obj.createTable("C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv");
//		obj.insertQueryStrings("C:/TempData/t.csv");

	}

	
	public void createTable(String csvFile) {
		// String csvFile =
		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		String createTableQueryString = "CREATE TABLE ";
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the schema name:");
		createTableQueryString += in.next() + ".";
		System.out.println("Please enter the Table name:");
		createTableQueryString += in.next() + " (";

		char tempInputString = ' ';

		List<String> typeOfColumns = new ArrayList<String>();
		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ",";

		int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			String[] columns = line.split(cvsSplitBy);
			System.out.println("Numbe of columns is " + columns.length + ".");
			for (; i < columns.length; ++i) {

				createTableQueryString += columns[i] + ' ';

				while (tempInputString != 'i' && tempInputString != 'd'
						&& tempInputString != 'v') {

					System.out
							.println("Please type the type of column #"
									+ (i + 1) + " whose name is \""
									+ columns[i] + "\"");
					System.out
							.println("Please enter \'i\' for integer, \'d\' for Date and \'v\' for Varchar(255):");

					String temp = in.next();
					tempInputString = temp.charAt(0);
					System.out.println("input is: " + tempInputString);
					switch (tempInputString) {
					case 'i':
						createTableQueryString += "int, ";
						break;

					case 'd':
						createTableQueryString += "date, ";
						break;

					case 'v':
						createTableQueryString += "varchar(255), ";
						break;

					default:
						System.out.println("Your input is not valid.");
						break;
					}

				}

				tempInputString = ' ';

			}

			// // use comma as separator
			//
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
		createTableQueryString = createTableQueryString.substring(0,
				createTableQueryString.length() - 2);
		createTableQueryString += ");";
		System.out.println("The \"Create Table\" SQL Query is:");
		System.out.println(createTableQueryString);
	}

	public void createTableWithStringBuilder(String csvFile) {
		// String csvFile =
		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		StringBuilder createTableQueryString = new StringBuilder(
				"CREATE TABLE ");
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the schema name:");
		createTableQueryString.append(in.next() + ".");
		System.out.println("Please enter the Table name:");
		createTableQueryString.append(in.next() + " (");

		char tempInputString = ' ';

		List<String> typeOfColumns = new ArrayList<String>();
		BufferedReader br = null;

		String line = "";
		String cvsSplitBy = ",";

		int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			String[] columns = line.split(cvsSplitBy);
			System.out.println("Numbe of columns is " + columns.length + ".");
			for (; i < columns.length; ++i) {

				createTableQueryString.append(columns[i] + ' ');

				while (tempInputString != 'i' && tempInputString != 'd'
						&& tempInputString != 'v') {

					System.out
							.println("Please type the type of column #"
									+ (i + 1) + " whose name is \""
									+ columns[i] + "\"");
					System.out
							.println("Please enter \'i\' for integer, \'d\' for Date and \'v\' for Varchar(255):");

					String temp = in.next();
					tempInputString = temp.charAt(0);
					System.out.println("input is: " + tempInputString);
					switch (tempInputString) {
					case 'i':
						createTableQueryString.append("int, ");
						break;

					case 'd':
						createTableQueryString.append("date, ");
						break;

					case 'v':
						createTableQueryString.append("varchar(255), ");
						break;

					default:
						System.out.println("Your input is not valid.");
						break;
					}

				}

				tempInputString = ' ';

			}

			// // use comma as separator
			//
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
		createTableQueryString.setLength(createTableQueryString.length() - 2);
		createTableQueryString.append(");");
		System.out.println("The \"Create Table\" SQL Query is:");
		System.out.println(createTableQueryString);
	}

	public void showColumns(String csvFile) {
		// String csvFile =
		// "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		List<String> typeOfColumns = new ArrayList<String>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		int i = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			line = br.readLine();

			// use comma as separator
			String[] columns = line.split(cvsSplitBy);
			System.out.println("number of columns is " + columns.length);
			for (; i < columns.length; ++i) {
				System.out.println("Please type the type of column #" + (i + 1)
						+ " whose name is \"" + columns[i] + "\"");
				Scanner in = new Scanner(System.in);

				typeOfColumns.add(in.next());

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

	}

	public void showRows() {

		// String csvFile =
		// transformSlashtoBackslash("C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv");
		String csvFile = "C:\\Users\\Navid.Vafaei\\dev\\db\\adncustomer_2014-06-18.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		int j = 0;
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] country = line.split(cvsSplitBy);

				System.out.println("Country [code= " + country[4] + " , name="
						+ country[5] + "]");

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
		transformSlashtoBackslash("f\\k/");
		System.out.println("Done");
	}

	public static String transformSlashtoBackslash(String original) {
		StringBuilder sb = new StringBuilder(original);
		// for (char ch : sb){
		// if (ch=='/'){
		// ch = '\\';
		//
		// }
		// System.out.println(original);
		//
		//
		// }
		for (int i = 0; i < sb.length(); ++i) {
			if (sb.charAt(i) == '\\') {
				sb.setCharAt(i, '/');
			}

		}
		System.out.println(sb);
		return original;
	}
}
