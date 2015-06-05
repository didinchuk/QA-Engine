package test;

import java.io.InputStream;
import java.net.URL;

import models.Datasource;
import models.DatasourceType;
import models.TempTables;
import navid.Table;

import org.avaje.agentloader.AgentLoader;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.enhance.agent.InputStreamTransform;
import com.avaje.ebean.enhance.agent.Transformer;

import dataLoader.ReadCVS;

public class helloWorld {
	public static void main(String[] args){
		System.out.println("Hello World!");
		//System.setProperty("catalina.base", "D:/apps/tomcat6");  
        //System.setProperty("ebean.props.file", "D:/apps/tomcat6/conf/zsite.ebean.properties");  
        //GlobalProperties.put("ebean.debug.sql", "true");  
          
		if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent","debug=1;packages=ivytest.**")) {
		    System.out.println("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
		  }
		
		if (!AgentLoader.loadAgentFromClasspath("avaje-ebeanorm-agent","debug=1;packages=models.**")) {
		    System.out.println("avaje-ebeanorm-agent not found in classpath - not dynamically loaded");
		  }
		
        /*EBasicVer e = Ebean.find(EBasicVer.class).where().eq("DEPT_ID", 100).findUnique();
        System.out.println(e.DEPT_ID);
        System.out.println(e.DNAME);
        System.out.println(e.MISSION);
        
        e.setId("11");
        System.out.println(e.DEPT_ID);
        Ebean.save(e);
        System.out.println("SAVED"); */
		
		/* DatasourceType dst = new DatasourceType();
		//dst.id = (long) 12;
		dst.datasourceTypeName = "main";
		dst.getTableDefinitionQuery = "temp";
		dst.getTableQuery = "temp";
		dst.jdbcJar = "temp";
		dst.save(); */
		
		//Datasource ds = new Datasource();
		
		TempTables tt  = new TempTables();
//		ReadCVS rCvs = new ReadCVS();
		Table t = new Table("C:/TempData/t.csv", "CAMPAIGNQA", "NAVID5");
		
		
		
		/*ds.connectionName = "My First Connection Navid";
		ds.username = "db2inst1";
		ds.password = "c5l@b123";
		ds.serverName = "cloud.clientspectrum.com";
		ds.databaseName = "CLOUD_DB";
		ds.serverPort = 5000;
		ds.datasourceType = Ebean.find(DatasourceType.class).where().eq("IDATASOURCETYPE", 1).findUnique();
		*/
		tt.createStatement = t.getCreateTableQueryString();
		tt.tempTableName = "Navid's Table";
		tt.id = (long) 1;
//		tt.save();
		
		
        //ds.save();
                  
        
        
        Datasource test = Datasource.find.byId((long)6);
        System.out.println(test.connectionName);
        System.out.println(test.id);
                
        Datasource test2 = Ebean.find(Datasource.class).where().eq("IDATASOURCEID",3).eq("IDATASOURCETYPE", 1).findUnique();
        System.out.println(test2.connectionName);
        System.out.println(test2.id);
          
	}
}
