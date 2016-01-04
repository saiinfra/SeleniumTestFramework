package com.salesforce.excel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

public class Test {

	private static Git git;
	private static CredentialsProvider cp;
	private static File dir;

	public static void main(String[] args) throws Exception {
		init();

	}

	private static void deleteDir(File file) {
	    File[] contents = file.listFiles();
	    if (contents != null) {
	        for (File f : contents) {
	            deleteDir(f);
	        }
	    }
	    file.delete();
	}
	
	public static void init() throws IOException {

		String name = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		cp = new UsernamePasswordCredentialsProvider(name, password);
		dir = new File(getCurrentPath() + "/" + "test");
		
		deleteDir(dir);
		// clone repository
		cloneRepository(cp, dir, url);
		addFiles(dir);
		commit(dir);
		push(cp, dir);
	}

	public static void cloneRepository(CredentialsProvider cp, File dir,
			String URL) {

		CloneCommand cc = new CloneCommand().setCredentialsProvider(cp)
				.setDirectory(dir).setURI(URL);

		try {
			cc.call();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void addFiles(File dir) {

		try {
			git = Git.open(dir);
			AddCommand ac = git.add();
			ac.addFilepattern(".");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void commit(File dir) {

		try {
			git = Git.open(dir);
			CommitCommand commit = git.commit();
			commit.setCommitter("sai", "skrishna@infrascape.com").setMessage(
					"Updated");
			commit.call();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void push(CredentialsProvider cp, File dir) {

		try {
			git = Git.open(dir);
			PushCommand pc = git.push();
			pc.setCredentialsProvider(cp).setForce(true).setPushAll();

			Iterator<PushResult> it = pc.call().iterator();
			if (it.hasNext()) {
				System.out.println(it.next().toString());
				System.out.println("Successfully Pushed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}
}
