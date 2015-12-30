package com.salesforce.util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppUtil {

	public static String getCurrentPath(){
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}
}
