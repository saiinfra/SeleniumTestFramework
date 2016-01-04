package com.salesforce.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;

import com.salesforce.domain.GitRepoDO;

public class RepoUtil {

	private static void copyFiles(File file) {
		File checkOutDir = new File(Constants.CheckoutPath1 + "/testcases/"
				+ file.getName());
		try {
			System.out.println(file.getName());
			Files.move(Paths.get(file.getPath()),
					Paths.get(checkOutDir.getPath()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void copySrcFiles(File file) {
		File checkOutDir = new File(Constants.CheckoutPath1 +Constants.DirSeperator+Constants.JavaSourcePath+Constants.DirSeperator
				+ file.getName());
		try {
			System.out.println(file.getName());
			Files.move(Paths.get(file.getPath()),
					Paths.get(checkOutDir.getPath()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void copyToMainSrc(File file) {
		File checkOutDir = new File(Constants.CheckoutPath1 +Constants.DirSeperator+Constants.JavaSourcePath+Constants.DirSeperator
				+ file.getName());
		try {
			System.out.println(AppUtil.getCurrentPath() + Constants.DirSeperator+Constants.JavaSourcePath1+Constants.DirSeperator);
			System.out.println(file.getName());
			Files.move(Paths.get(checkOutDir.getPath()),
					Paths.get(AppUtil.getCurrentPath() + Constants.DirSeperator+Constants.JavaSourcePath+Constants.DirSeperator+file.getName()),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void CheckIn(GitRepoDO gitRepoDO, String sourcePath, File file) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		copyFiles(file);
		try {
			RepoClass.addFile(git);
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RepoClass.commit(git, gitRepoDO);
	}
	public static void CheckOut(GitRepoDO gitRepoDO) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static void CheckInSrc(GitRepoDO gitRepoDO, String sourcePath, File file) {
		File checkOutDir = new File(Constants.CheckoutPath1);
		RepoClass.deleteDirectory(checkOutDir);
		Git git = null;
		try {
			git = RepoClass.cloneRepository(gitRepoDO, checkOutDir);
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//copy to checkout src/com/test
		copySrcFiles(file);
		
		//copy to main src/com/test
		copyToMainSrc(file);
		try {
			RepoClass.addFile(git);
		} catch (IOException | GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RepoClass.commit(git, gitRepoDO);
	}
	

	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}
}
