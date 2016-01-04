package com.salesforce.util;

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
import org.eclipse.jgit.api.errors.AbortedByHookException;
import org.eclipse.jgit.api.errors.ConcurrentRefUpdateException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.api.errors.NoMessageException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.api.errors.UnmergedPathsException;
import org.eclipse.jgit.api.errors.WrongRepositoryStateException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.salesforce.domain.GitRepoDO;

public class RepoClass {

	private static Git git;
	private static CredentialsProvider cp;
	private static File dir = new File(getCurrentPath() + "/"
			+ Constants.CheckoutPath1);

	public static String getCurrentPath() {
		Path currentRelativePath = Paths.get("");
		String path = currentRelativePath.toAbsolutePath().toString();
		return path;
	}

	/**
	 * Delete directory even if not empty
	 */
	public static void deleteDirectory(File dirPath) {

		if (!dirPath.exists()) {
			return;
		}

		for (String filePath : dirPath.list()) {
			File file = new File(dirPath, filePath);
			if (file.isDirectory())
				deleteDirectory(file);
			file.delete();
		}
	}

	public RepoClass() throws InvalidRemoteException, TransportException,
			GitAPIException, IOException {
		deleteDirectory(dir);
		// cloneRepository();
		addFile();
		//commit();
	}

	public static void CheckIn(File file) throws IOException {
		deleteDirectory(dir);
		// cloneRepository();
		// addFiles(dir);
		// commit(dir);
		// push(cp, dir);
	}

	public static void main(String[] args) throws InvalidRemoteException,
			TransportException, GitAPIException, IOException {
		RepoClass t = new RepoClass();
		// deleteDirectory(dir);
		// init1();
	}

	public static Git cloneRepository(GitRepoDO gitRepoDO, File chekOutDir)
			throws InvalidRemoteException, TransportException, GitAPIException {
		// clone
		CloneCommand cc = new CloneCommand().setCredentialsProvider(getCredentialsProvider(gitRepoDO))
				.setDirectory(chekOutDir).setURI(gitRepoDO.getUrl());
		git = null;
		try {
			git = cc.call();
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return git;
	}

	public static CredentialsProvider getCredentialsProvider(GitRepoDO gitRepoDO){
		// credentials
		CredentialsProvider cp = new UsernamePasswordCredentialsProvider(
				gitRepoDO.getUserName(), gitRepoDO.getPassword());
		return cp;
	}
	
	public void addFile() throws IOException, NoFilepatternException,
			GitAPIException {
		String fileName = "abc7.txt";
		File myfile = null;
		try {
			myfile = new File(git.getRepository().getDirectory().getParent()
					+ "/testcases", fileName);
			myfile.createNewFile();
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}

		// add
		AddCommand ac = git.add();
		ac.addFilepattern(".");
		try {
			ac.call();
		} catch (NoFilepatternException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addFile(String fileName, Git git) throws IOException,
			NoFilepatternException, GitAPIException {
		File myfile = null;
		try {
			myfile = new File(git.getRepository().getDirectory().getParent()
					+ "/testcases", fileName);
			myfile.createNewFile();
		} catch (java.lang.NullPointerException e) {
			e.printStackTrace();
		}

		// add
		AddCommand ac = git.add();
		ac.addFilepattern(".");
		try {
			ac.call();
		} catch (NoFilepatternException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addFile(Git git) throws IOException,
			NoFilepatternException, GitAPIException {
		// add
		AddCommand ac = git.add();
		ac.addFilepattern(".");
		try {
			ac.call();
		} catch (NoFilepatternException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void commit(Git git, GitRepoDO gitRepoDO) {
		// commit
		CommitCommand commit = git.commit();
		commit.setCommitter("TMall", "open@tmall.com").setMessage("push war");
		try {
			commit.call();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (NoMessageException e) {
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AbortedByHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// push
		PushCommand pc = git.push();
		pc.setCredentialsProvider(getCredentialsProvider(gitRepoDO)).setForce(true).setPushAll();
		try {
			Iterator<PushResult> it = pc.call().iterator();
			if (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}

	public static void init1() {
		String name = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";

		// credentials
		CredentialsProvider cp = new UsernamePasswordCredentialsProvider(name,
				password);
		// clone
		File dir = new File(getCurrentPath() + "/" + "test");
		CloneCommand cc = new CloneCommand().setCredentialsProvider(cp)
				.setDirectory(dir).setURI(url);
		Git git = null;
		try {
			git = cc.call();
		} catch (GitAPIException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		File myfile = new File(git.getRepository().getDirectory().getParent(),
				"abc5.txt");
		try {
			myfile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// add
		AddCommand ac = git.add();
		ac.addFilepattern(".");
		try {
			ac.call();
		} catch (NoFilepatternException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// commit
		CommitCommand commit = git.commit();
		commit.setCommitter("TMall", "open@tmall.com").setMessage("push war");
		try {
			commit.call();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (NoMessageException e) {
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		} catch (UnmergedPathsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AbortedByHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GitAPIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// push
		PushCommand pc = git.push();
		pc.setCredentialsProvider(cp).setForce(true).setPushAll();
		try {
			Iterator<PushResult> it = pc.call().iterator();
			if (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		} catch (GitAPIException e) {
			e.printStackTrace();
		}

		// cleanup
		dir.deleteOnExit();
	}

	public static void init() throws InvalidRemoteException,
			TransportException, GitAPIException, IOException {
		String name = "skrishna@infrascape.com";
		String password = "Yarragsa@01";
		String url = "https://github.com/saiinfra/CustomerTestProject.git";
		// credentials
		cp = new UsernamePasswordCredentialsProvider(name, password);
		// clone

		File dir = new File(getCurrentPath() + "/" + "test");
		CloneCommand cc = new CloneCommand().setCredentialsProvider(cp)
				.setDirectory(dir).setURI(url);
		Git git = cc.call();
		File myfile = new File(git.getRepository().getDirectory().getParent(),
				"abc4.txt");
		myfile.createNewFile();

		// run the add-call
		git.add().addFilepattern("abc5.txt").call();

		// commit
		CommitCommand commit = git.commit();
		commit.setCommitter("TMall", "open@tmall.com").setMessage("push war");
		try {
			commit.call();
		} catch (NoHeadException e) {
			e.printStackTrace();
		} catch (NoMessageException e) {
			e.printStackTrace();
		} catch (ConcurrentRefUpdateException e) {
			e.printStackTrace();
		} catch (WrongRepositoryStateException e) {
			e.printStackTrace();
		}
		// push
		PushCommand pc = git.push();
		pc.setCredentialsProvider(cp).setForce(true).setPushAll();
		try {
			Iterator<PushResult> it = pc.call().iterator();
			if (it.hasNext()) {
				System.out.println(it.next().toString());
			}
		} catch (InvalidRemoteException e) {
			e.printStackTrace();
		}

		// cleanup
		dir.deleteOnExit();
	}
}
