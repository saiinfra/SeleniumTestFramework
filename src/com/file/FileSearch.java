package com.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.salesforce.util.Constants;

public class FileSearch {

	private static String fileNameToSearch;
	private static List<String> result = new ArrayList<String>();
	
	
	/*public String getFileNameToSearch() {
		return fileNameToSearch;
	}

	public static void setFileNameToSearch(String fileNameToSearch) {
		fileNameToSearch = fileNameToSearch;
	}
*/
	public static List<String> getResult() {
		return result;
	}

	public static void main(String[] args) {

	/*	FileSearch fileSearch = new FileSearch();

		// try different directory and filename :)
		fileSearch.searchDirectory(new File(searchPath), "MetadataLogSQLStmts.java");

		int count = fileSearch.getResult().size();
		if (count == 0) {
			System.out.println("\nNo result found!");
		} else {
			System.out.println("\nFound " + count + " result!\n");
			for (String matched : fileSearch.getResult()) {
				System.out.println("Found : " + matched);
			}
		}
*/		getPath("TS_000000001.java");
		}

	public static String getPath(String fileName){
		String path = "NotFound";
		// try different directory and filename :)
		result = new ArrayList<String>();
		searchDirectory(new File(Constants.CheckoutFilePath), fileName);
		
		int count = getResult().size();
		if (count == 0) {
			System.out.println("\nNo result found!");
		} else {
			System.out.println("\nFound " + count + " result!\n");
			for (String matched : getResult()) {
				System.out.println("Found : " + matched);
				path =  matched;
			}
		}
		return path;
	}
	
	public static void searchDirectory(File directory, String fileName) {

		fileNameToSearch = fileName;
		if (directory.isDirectory()) {
			search(directory,fileName);
		} else {
			System.out.println(directory.getAbsoluteFile() + " is not a directory!");
		}

	}

	private static void search(File file,  String fileNameToSearch) {

		if (file.isDirectory()) {
			//System.out.println("Searching directory ... " + file.getAbsoluteFile());

			// do you have permission to read this directory?
			if (file.canRead()) {
				for (File temp : file.listFiles()) {
					if (temp.isDirectory()) {
						search(temp, fileNameToSearch);
					} else {
						//System.out.println("fileNameToSearch: "+fileNameToSearch+" - temp.getName(): "+temp.getName());
						if (fileNameToSearch.equals(temp.getName())) {
							result.add(temp.getAbsoluteFile().toString());
						}
					}
				}
			} else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			}
		}

	}

}
