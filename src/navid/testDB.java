package navid;

import models.Datasource;
import models.TempTables;
import navid.Table;

import org.avaje.agentloader.AgentLoader;

import com.avaje.ebean.Ebean;

public class testDB {
	public static void main(String[] args) {
		System.out.println("Hello World!");


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

		TempTables tt = new TempTables();
		// ReadCVS rCvs = new ReadCVS();
		Table t = new Table("C:/TempData/t.csv", "CAMPAIGNQA", "NAVID5");

		/*
		 * ds.connectionName = "My First Connection Navid"; ds.username =
		 * "db2inst1"; ds.password = "c5l@b123"; ds.serverName =
		 * "cloud.clientspectrum.com"; ds.databaseName = "CLOUD_DB";
		 * ds.serverPort = 5000; ds.datasourceType =
		 * Ebean.find(DatasourceType.class).where().eq("IDATASOURCETYPE",
		 * 1).findUnique();
		 */
		tt.createStatement = t.getCreateTableQueryString();
		tt.tempTableName = "Navid's Table";
		tt.id = (long) 1;
		// tt.save();

		// ds.save();

		Datasource test = Datasource.find.byId((long) 6);
		System.out.println(test.connectionName);
		System.out.println(test.id);

		Datasource test2 = Ebean.find(Datasource.class).where()
				.eq("IDATASOURCEID", 3).eq("IDATASOURCETYPE", 1).findUnique();
		System.out.println(test2.connectionName);
		System.out.println(test2.id);

	}
}
