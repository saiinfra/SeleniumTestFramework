package com.salesforce.util;

public class SalesForceUtil {
	
	private static SFoAuthHandle sfHandle;
	
	public SalesForceUtil(){
		super();
	}
	
	public static SFoAuthHandle getSFHandle(){
		System.out.println("hello sfOauthhandle");
		sfHandle = new SFoAuthHandle(Constants.USERID, Constants.PASSWORD, Constants.INSTANCE_URL, "");
		return sfHandle;
	}
}
