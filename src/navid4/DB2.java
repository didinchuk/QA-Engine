package navid4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import models.TempTables;

import org.avaje.agentloader.AgentLoader;

import com.avaje.ebean.Ebean;
import com.ibm.db2.jcc.a.a;
import com.ibm.db2.jcc.am.ne;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import dataLoader.insertDataToTable;
import dataLoader.tempInputInterface;

public class DB2 {

	// max value that is acceptable
	int maxBatchInsert = 136;
	ArrayList<String> insertQueriesAttributeArrayList;

	public DB2() {
		 insertQueriesAttributeArrayList = new ArrayList<String>();
	}

	public void showInsertQueryArrayList() {
		System.out.println();
		if (insertQueriesAttributeArrayList.size() == 0) {
			System.out.println("insertQueriesAttributeArrayList is empty. :( ");
		} else {
			System.out.println("Here are the list of insert queries:");
			for (String string : insertQueriesAttributeArrayList)
				System.out.println(string);

		}

	}

	// what if one of the data has ',' in it?
	public String createTableString(Data d) {
		StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");

		stringBuilder.append(Common.schemaNameString);
		stringBuilder.append(".");
		stringBuilder.append(d.tableNameString);
		stringBuilder.append(" (");
		for (Column column : d.header.columns) {
			stringBuilder.append(column.name);
			stringBuilder.append(" ");
			stringBuilder.append(column.dataType);
			stringBuilder.append(", ");
		}
		// System.out.println(stringBuilder);

		stringBuilder.setCharAt((stringBuilder.length() - 2), ')');
		System.out.println(stringBuilder);

		return stringBuilder.toString();

	}

	public void update_QA_TEMPTABLES(String createTableString,
			String tableNameString) {

		if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent",
				"debug=1;packages=ivytest.**")) {
			System.out
					.println("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
		}

		if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent",
				"debug=1;packages=models.**")) {
			System.out
					.println("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
		}

		TempTables tables = new TempTables();
		tables.id = Ebean.find(TempTables.class).order("ITEMPTABLEID desc")
				.setMaxRows(1).findUnique().id + 1;
		tables.createStatement = createTableString;
		tables.tempTableName = tableNameString;
		tables.save();
	}

	public void exectueCreateTable(String createTableString) {

		Connection connection = null;
		Statement selectStmt = null;
		try {
			Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
			connection = DriverManager.getConnection(
					"jdbc:db2://cloud.clientspectrum.com:50000/CLOUD_DB",
					"db2inst1", "c5l@b123");

			selectStmt = connection.createStatement();

			int result = selectStmt.executeUpdate(createTableString);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				// insertStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// write the code so that all (portion) of data is added with
	// as few statements as possible considering the buffer limit

	public void exectueInsertData() {

		Connection connection = null;
		Statement selectStmt = null;
		try {
			Class.forName("COM.ibm.db2.jdbc.app.DB2Driver");
			connection = DriverManager.getConnection(
					"jdbc:db2://cloud.clientspectrum.com:50000/CLOUD_DB",
					"db2inst1", "c5l@b123");

			selectStmt = connection.createStatement();

			for (String eachRowInsertQuery : insertQueriesAttributeArrayList) {

				int result = selectStmt.executeUpdate(eachRowInsertQuery);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				selectStmt.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void getMaxBatchInsert() {
		System.out.println("Please type the maximum batch for insert:");
		Scanner in = new Scanner(System.in);
		maxBatchInsert = in.nextInt();

	}

	public ArrayList<String> makeInsertDataQueryWithMaxBatchLimit(Data d) {
		getMaxBatchInsert();
		ArrayList<String> insertQueriesList = new ArrayList<String>();
		StringBuilder patternStringBuilder = new StringBuilder("INSERT INTO ");
		patternStringBuilder.append(d.schemaNameString);
		patternStringBuilder.append(".");
		patternStringBuilder.append(d.tableNameString);
		patternStringBuilder.append(" (");
		for (Column column : d.header.columns) {
			patternStringBuilder.append(column.name);
			patternStringBuilder.append(",");
		}

		patternStringBuilder
				.setCharAt((patternStringBuilder.length() - 1), ')');

		patternStringBuilder.append(" VALUES (");

		System.out.println("patternStringBuilder is: ");
		System.out.println(patternStringBuilder);
		int count = 0;
		StringBuilder incrementalStringBuilder = new StringBuilder(
				patternStringBuilder);

		// what if there is not enough space even till here?! :P
		if (incrementalStringBuilder.length() > maxBatchInsert) {
			System.out
					.println("\nThere is not enough space for creating even the basics of an insert query which is:");
			System.out.println(incrementalStringBuilder);

			System.out.println("because its length is: "
					+ (incrementalStringBuilder.length()));

			// insertQueriesAttributeArrayList = insertQueriesList;
			return null;
		}

		for (; count < d.allRowsLists.size(); count++) {
			StringBuilder tempBuilder = new StringBuilder();

			for (int i = 0; i < d.allRowsLists.get(count).size(); i++) {
				if (d.header.columns.get(i).dataType.equalsIgnoreCase("INT")
						|| d.header.columns.get(i).dataType
								.equalsIgnoreCase("SMALLINT")) {

					tempBuilder.append(d.allRowsLists.get(count).get(i));
				} else {
					tempBuilder.append('\'');
					tempBuilder.append(d.allRowsLists.get(count).get(i));
					tempBuilder.append('\'');
				}
				tempBuilder.append(",");
				// System.out.println("count = " + count + " i = " + i +
				// "temp = ");
				// System.out.println(tempBuilder);
			}

			tempBuilder.setCharAt((tempBuilder.length() - 1), ')');
			tempBuilder.append(",(");

			// System.out.println("count = " + count +" and tempBuilder is:");
			// System.out.println(tempBuilder);

			if (incrementalStringBuilder.length() + tempBuilder.length() - 2 <= maxBatchInsert) {
				incrementalStringBuilder.append(tempBuilder);

				// System.out.println("count = " + count
				// +" and incrementalStringBuilder is:");
				// System.out.println(incrementalStringBuilder);

			} else {
				if (count > 0) {
					incrementalStringBuilder.setLength(incrementalStringBuilder
							.length() - 2);
				} else {
					System.out.println("\nThere is not enough space for executing even the first insert query which is:");
					tempBuilder.setLength(tempBuilder.length() - 2);
					System.out.println(incrementalStringBuilder.toString()
							+ tempBuilder);

					System.out.println("because its length is: "
							+ (incrementalStringBuilder.length() + tempBuilder
									.length()));
					System.out.println();
					insertQueriesAttributeArrayList = insertQueriesList;
					return insertQueriesList;
				}
				// incrementalStringBuilder.setCharAt((incrementalStringBuilder.length()
				// - 1), ')');

				insertQueriesList.add(incrementalStringBuilder.toString());
				System.out.println(insertQueriesList.toString());
				incrementalStringBuilder.setLength(patternStringBuilder
						.length());
				if (incrementalStringBuilder.length() + tempBuilder.length()
						- 2 <= maxBatchInsert) {
					incrementalStringBuilder.append(tempBuilder);
					// System.out.println(incrementalStringBuilder);

				} else {
					System.out
							.println("\nthis query is too long to be executed:");
					tempBuilder.setLength(tempBuilder.length() - 2);
					System.out.println(incrementalStringBuilder.toString()
							+ tempBuilder);

					System.out.println("because its length is: "
							+ (incrementalStringBuilder.length() + tempBuilder
									.length()));
					insertQueriesAttributeArrayList = insertQueriesList;
					return insertQueriesList;
				}

			}

		}
		incrementalStringBuilder
				.setLength(incrementalStringBuilder.length() - 2);

		insertQueriesList.add(incrementalStringBuilder.toString());
		System.out.println(incrementalStringBuilder);
		// incrementalStringBuilder.setCharAt(incrementalStringBuilder.length()
		// - 1, ')');

		insertQueriesAttributeArrayList = insertQueriesList;
		return insertQueriesList;

	}

	public ArrayList<String> makeInsertDataQuery(Data d) {
		ArrayList<String> insertQueriesList = new ArrayList<String>();
		StringBuilder mainStringBuilder = new StringBuilder("INSERT INTO ");
		mainStringBuilder.append(d.schemaNameString);
		mainStringBuilder.append(".");
		mainStringBuilder.append(d.tableNameString);
		mainStringBuilder.append(" (");
		for (Column column : d.header.columns) {
			mainStringBuilder.append(column.name);
			mainStringBuilder.append(",");
		}

		mainStringBuilder.setCharAt((mainStringBuilder.length() - 1), ')');

		mainStringBuilder.append(" VALUES (");

		System.out.println(mainStringBuilder);
		int count = 0;
		for (ArrayList<String> aList : d.allRowsLists) {
			StringBuilder tempInsertQueryString = new StringBuilder(
					mainStringBuilder);
			for (int i = 0; i < aList.size(); i++) {

				if (d.header.columns.get(i).dataType.equalsIgnoreCase("INT")) {

					tempInsertQueryString.append(aList.get(i));
				} else {
					tempInsertQueryString.append('\'');
					tempInsertQueryString.append(aList.get(i));
					tempInsertQueryString.append('\'');
				}
				tempInsertQueryString.append(",");

			}

			tempInsertQueryString.setCharAt(
					(tempInsertQueryString.length() - 1), ')');
			System.out.println(tempInsertQueryString);
			insertQueriesList.add(tempInsertQueryString.toString());

			++count;
		}

		insertQueriesAttributeArrayList = insertQueriesList;
		return insertQueriesList;

	}

}
