package navid3;

import java.util.ArrayList;


import java.util.Scanner;
//
//import navid3.Data;
//import navid3.FileProcessor;

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
			System.out.println("Please enter the path like:\n" + "C:/tables/table.csv");
			this.filePath = in.next();
			in.close();
		}
		
		public void askHasHeaders() {
			Scanner in = new Scanner(System.in);
			System.out.println("Does your file have headers? (y/n):");
			String temp = in.next();
			in.close();
			if(temp == "y" || temp == "Y"){
				this.hasHeader = true;
			}else if(temp == "n" || temp == "N"){
				this.hasHeader = false;
			}else{
				this.hasHeader = false;
			}
		}
		
		public void askDelimiter() {
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter a delimiter to use:");
			this.delimiter = in.next();
			in.close();
		}

		public void askRowLimit() {
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter a row limit");
			this.rowLimit = Integer.parseInt(in.next());
			in.close();
		}
		
		public void askIgnoreLines() {
			Scanner in = new Scanner(System.in);
			System.out.println("Please enter lines to ignore");
			
			this.ignoreLines = Integer.parseInt(in.next());
			in.close();
		}

		public void askHeaderNames(int columnCount) {
			Scanner in = new Scanner(System.in);
			for (int i = 0; i < columnCount; i++) {
				System.out.println("Please enter the column name for column#"+(i + 1));
//				String tempHeaderNameString = in.next();
				
				columnsHeaderList.add(in.nextLine());
			
				
			}
			
			in.close();
			

		}

		public boolean getHeaders() {
			return hasHeader;
		}

			
			

		public void run() {

			intro();
			/*
			getFilePath();
			askHasHeaders();
			askDelimiter();
			askRowLimit();
			askIgnoreLines();
			*/
			/*
			fr.setDelimiter(this.delimiter);
			fr.setRowLimit(this.rowLimit);
			fr.setIgnoreLines(this.ignoreLines);
			fr.setHasHeader(this.hasHeader);
			*/
			this.filePath ="C:/TempData/t.csv";
			this.hasHeader = true;
			fr.setDelimiter(",");
			fr.setRowLimit(1);
			fr.setIgnoreLines(1);
			fr.setHasHeader(this.hasHeader);
			
			columnCount = fr.countColumns(this.filePath);
			if(!this.hasHeader){
				//receives headers name from the user and updates InterfaceTest.columnsHeaderList
				askHeaderNames(columnCount);
				
				data = fr.read(filePath,this.columnsHeaderList);
			}else{
				data = fr.read(filePath);
			}
			data.showHeaders();
			data.showAllRows();
			
			
		}
		
		public void intro(){
			System.out.println("Welcome");
		}

		public static void main(String[] args) {
			InterfaceTest ii = new InterfaceTest();
			ii.run();
		}

	}


