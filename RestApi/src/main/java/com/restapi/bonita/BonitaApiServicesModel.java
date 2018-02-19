package com.restapi.bonita;

import org.apache.commons.httpclient.HttpClient;
import org.json.JSONObject;

public class BonitaApiServicesModel 
{
	HttpClient client = new HttpClient();
	/*
	String server = "http://localhost:8080/";
	String username = "walter.bates";
	String password = "bpm";
	*/
	String server = new String();
	String username = new String();
	String password = new String();
	
	int responseCode = 200;  // Success
	String responseEntity = "";
	int statusCode = 0;  // Success
	String statusMessage = "Success";  // Success
	String result = "";
	
	String processName = "";
	String processVersion= "";
	String createdByUserName = "";
	String sessionId = "";
	
	Long processDefinitionId = new Long("0");
	Long createdByUserId = new Long("0");
	Long caseId = new Long("0");
	
	public HttpClient getClient() {
		return client;
	}
	public void setClient(HttpClient client) {
		this.client = client;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseEntity() {
		return responseEntity;
	}
	public void setResponseEntity(String responseEntity) {
		this.responseEntity = responseEntity;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessVersion() {
		return processVersion;
	}
	public void setProcessVersion(String processVersion) {
		this.processVersion = processVersion;
	}
	public String getCreatedByUserName() {
		return createdByUserName;
	}
	public void setCreatedByUserName(String createdByUserName) {
		this.createdByUserName = createdByUserName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public Long getProcessDefinitionId() {
		return processDefinitionId;
	}
	public void setProcessDefinitionId(Long processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}
	public Long getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(Long createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public Long getCaseId() {
		return caseId;
	}
	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}
	
	
	public void reset() 
	{
		responseCode = 200;
		responseEntity = "";
		statusCode = 0; // Success
		statusMessage = "Success"; // Success
		result = "";

		processName = "";
		processVersion = "";
		createdByUserName = "";
		sessionId = "";

		processDefinitionId = 0L;
		createdByUserId = 0L;
		caseId = 0L;
	}

	
	public String toString()
	{
		String additionalDetails = "";
		JSONObject jsonOutput = null;
		
		try
		{
			if (this.result.length() > 0) 
			{
				additionalDetails = this.result.replace("\"", "\\\"");
			}

			jsonOutput = new JSONObject("{ " +
			" \"statusCode\":" + this.statusCode +
			",\"statusMessage\":\"" + this.statusMessage.replace("\"", "\\\"") + "\"" +
			//",\"sessionCookie\":\"" + this.sessionId + "\"" +
			",\"processName\":\"" + this.processName + "\"" +
			",\"processVersion\":\"" + this.processVersion + "\"" +
			//",\"processDefinitionId\":" + this.processDefinitionId + 
			//",\"createdByUserName\":\"" + this.createdByUserName + "\"" +
			//",\"createdByUserId\":" + this.createdByUserId + 
			",\"caseId\":" + this.caseId + 
			",\"additionalDetails\":\"" + additionalDetails + "\"" +
			//",\"server\":\"" + this.server + "\"" +
			//",\"username\":\"" + this.username + "\"" +
			" }");

			return jsonOutput.toString(2);
		}
		catch (Exception e)
		{
			return "{ \"exception\": \""+e.getMessage().replace("\"", "\\\"") + "\" }";
		}
		
	}  // End of toString
	

}  // End of BonitaApiServicesModel