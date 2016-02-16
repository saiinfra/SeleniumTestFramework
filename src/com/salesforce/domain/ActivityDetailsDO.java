package com.salesforce.domain;

public class ActivityDetailsDO {

	String id;
	String actionType;
	String commandType;
	String comparationData;
	String ComponentName;
	String data;
	String elementLocationType;
	String isAssertionCheck;
	Double step;
	String testScriptDetails;
	String webElementType;

	public ActivityDetailsDO(String id, String actionType, String commandType,
			String comparationData, String ComponentName, String data,
			String elementLocationType, String isAssertionCheck, Double step,
			String testScriptDetails, String webElementType) {

		this.id = id;
		this.actionType = actionType;
		this.commandType = commandType;
		this.comparationData = comparationData;
		this.ComponentName = ComponentName;
		this.data = data;
		this.elementLocationType = elementLocationType;
		this.isAssertionCheck = isAssertionCheck;
		this.step = step;
		this.testScriptDetails = testScriptDetails;
		this.webElementType = webElementType;

	}

	public ActivityDetailsDO(String actionType, String commandType,
			String comparationData, String ComponentName, String data,
			String elementLocationType, String isAssertionCheck, Double step,
			String testScriptDetails, String webElementType) {

		this.actionType = actionType;
		this.commandType = commandType;
		this.comparationData = comparationData;
		this.ComponentName = ComponentName;
		this.data = data;
		this.elementLocationType = elementLocationType;
		this.isAssertionCheck = isAssertionCheck;
		this.step = step;
		this.testScriptDetails = testScriptDetails;
		this.webElementType = webElementType;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getComparationData() {
		return comparationData;
	}

	public void setComparationData(String comparationData) {
		this.comparationData = comparationData;
	}

	public String getComponentName() {
		return ComponentName;
	}

	public void setComponentName(String componentName) {
		ComponentName = componentName;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getElementLocationType() {
		return elementLocationType;
	}

	public void setElementLocationType(String elementLocationType) {
		this.elementLocationType = elementLocationType;
	}

	public String getIsAssertionCheck() {
		return isAssertionCheck;
	}

	public void setIsAssertionCheck(String isAssertionCheck) {
		this.isAssertionCheck = isAssertionCheck;
	}

	public Double getStep() {
		return step;
	}

	public void setStep(Double step) {
		this.step = step;
	}

	public String getTestScriptDetails() {
		return testScriptDetails;
	}

	public void setTestScriptDetails(String testScriptDetails) {
		this.testScriptDetails = testScriptDetails;
	}

	public String getWebElementType() {
		return webElementType;
	}

	public void setWebElementType(String webElementType) {
		this.webElementType = webElementType;
	}

}
