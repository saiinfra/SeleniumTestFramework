package com.salesforce.util;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class SFoAuthHandle {

	String oAuthToken;
	String orgId;
	String userId = "";
	String passwd = "";
	String securityToken = "";
	ConnectorConfig enterpriseConfig = null;
	ConnectorConfig metadataConfig = null;
	ConnectorConfig toolingConfig = null;
	ConnectorConfig bulkConfig = null;
	MetadataConnection metadataConnection = null;
	EnterpriseConnection enterpriseConnection = null;
	String serverURL = null;
	String refreshtoken = null;
	String orgType = null;
	String aa=null;

	public String getAa() {
		return aa;
	}

	public void setAa(String aa) {
		this.aa = aa;
	}

	
	public SFoAuthHandle(String userId, String passwd,String serverURL,String aa)
			 {
		this.userId = userId;
		this.passwd = passwd;
		this.serverURL=serverURL;
		this.aa=aa;
	}



	public EnterpriseConnection getEnterpriseConnection()  {
		enterpriseConfig = new ConnectorConfig();
		if (enterpriseConnection == null) {
			if (getoAuthToken() != null && !getoAuthToken().isEmpty()) {
				enterpriseConfig.setSessionId(getoAuthToken());
				enterpriseConfig.setManualLogin(true);
				enterpriseConfig.setCompression(false);
				String serviceEndpoint = null;
				serviceEndpoint = getServerURL() + "/services/Soap/c/33.0";
				enterpriseConfig.setServiceEndpoint(serviceEndpoint);
				try {
					enterpriseConnection = Connector
							.newConnection(enterpriseConfig);
				} catch (ConnectionException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// System.out.println(e.toString());
					e.printStackTrace();
				
				}
			} else if (getUserId() != null && getPasswd() != null) {
				try {
					enterpriseConfig.setUsername(getUserId());
					enterpriseConfig.setPassword(getPasswd());
					String serviceEndpoint = null;
					serviceEndpoint = getServerURL() + "/services/Soap/c/33.0";
					enterpriseConfig.setAuthEndpoint(serviceEndpoint);
					enterpriseConfig.setTraceFile("trace.log");
					enterpriseConfig.setTraceMessage(true);
					enterpriseConfig.setPrettyPrintXml(true);
					enterpriseConnection = Connector
							.newConnection(enterpriseConfig);
				} catch (Exception e) {
					e.printStackTrace();
					// System.out.println(e.toString());
					
				}
			}
		}
		return enterpriseConnection;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getoAuthToken() {
		return oAuthToken;
	}

	public void setoAuthToken(String oAuthToken) {
		this.oAuthToken = oAuthToken;
	}

	public String getServerURL() {
		return serverURL;
	}

	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}

	public void setMetadataConnection(MetadataConnection metadataConnection) {
		this.metadataConnection = metadataConnection;
	}

	public void setEnterpriseConnection(
			EnterpriseConnection enterpriseConnection) {
		this.enterpriseConnection = enterpriseConnection;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String toString() {
		return new String(getOrgId() + "~" + getoAuthToken() + "~"
				+ getServerURL() + "~" + getRefreshtoken() + "~" + getOrgType());
	}

	
}
