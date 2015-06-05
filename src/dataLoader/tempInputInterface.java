package dataLoader;

import java.io.FileReader;
import java.util.HashMap;

import navid.Data;

public class tempInputInterface {
	Data data;
	FileReader fileReader;
	String pathString;
	
	public tempInputInterface(String pathString) {
		if (pathString == null || pathString == ""){
			//!!! make sure you change this part in the actual code.
			pathString = "C:/TempData/t.csv";
		}
		
		
		
//		fileReader = new FileReader();
		
		
		
	}
	
	public String getPath(String pathString) {
		if (pathString == null || pathString == ""){
			//!!! make sure you change this part in the actual code.
			pathString = "C:/TempData/t.csv";
		}
		
		return null;
	
	}
	
//
//	public HashMap<String, String> getColumnTypes() {
//		
//	}
//		
		
		
		
		
	

}
