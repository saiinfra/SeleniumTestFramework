package com.salesforce.domain;

public class TestInformationDO {

	private String id;
	private String application;
	private String description;
	private Double errors;
	private String executionURL;
	private String modulename;
	private String organizationid;
	private String priority;
	private String title;
	public TestInformationDO(String id)
	{
		this.id=id;
		
		
	}

	public TestInformationDO(String id,String application,String description,Double errors,String executionURL,String modulename,String organizationid,String priority,String title)
	{
		this.id=id;
		this.application=application;
		this.description=description;
		this.errors=errors;
		this.executionURL=executionURL;
		this.modulename=modulename;
		this.organizationid=organizationid;
		this.priority=priority;
		this.title=title;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getErrors() {
		return errors;
	}

	public void setErrors(Double errors) {
		this.errors = errors;
	}

	public String getExecutionURL() {
		return executionURL;
	}

	public void setExecutionURL(String executionURL) {
		this.executionURL = executionURL;
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getOrganizationid() {
		return organizationid;
	}

	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

}
