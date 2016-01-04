package com.salesforce.domain;

import java.io.File;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.CredentialsProvider;

import com.salesforce.util.Constants;

public class GitRepoDO {
	private String userName = "skrishna@infrascape.com";
	private String password = "Yarragsa@01";
	private String url = "https://github.com/saiinfra/CustomerTestProject.git";
	private Git git;
	private CredentialsProvider cp;
	
	public GitRepoDO(String userName,String password, String url ){
		this.userName = userName;
		this.password = password;
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
