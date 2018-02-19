package com.restapi.bonita;

import javax.ws.rs.core.Response;

import java.net.URLEncoder;



import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.restapi.bonita.BonitaApiServicesModel;


public class BonitaApiServicesImpl 
{
	private BonitaApiServicesModel model;
//	private static final Logger logger = Logger.getLogger(
//		    Thread.currentThread().getStackTrace()[0].getClassName() );
	private static final Logger logger = Logger.getLogger(BonitaApiServicesImpl.class);

	public BonitaApiServicesImpl() 
	{
		this.model = new BonitaApiServicesModel();
		

		// Get connection details from environment
		try
		{
			String bonitaServer = System.getenv("BONITA_SERVER");
			if (bonitaServer.length() > 0 )
			{
				model.setServer(bonitaServer);
			}
		}
		catch (Exception e)
		{
			model.setServer("http://localhost:8080/");
		}
		
		try
		{
			String bonitaUsername = System.getenv("BONITA_USERNAME");
			if (bonitaUsername.length() > 0)
			{
				model.setUsername(bonitaUsername);
			}
			String bonitaPassword = System.getenv("BONITA_PASSWORD");
			if (bonitaPassword.length() > 0)
			{
				model.setPassword(bonitaPassword);
			}
		}
		catch (Exception e)
		{
			model.setUsername("walter.bates");
			model.setPassword("bpm");
		}
	}  // End of BonitaApiServicesImpl default constructor
	
	
	public Response sendMessageImpl(String payload) 
	{
		int statusCode = 0;
		// Establish API session with Bonita engine
		logger.info("model---"+model.getUsername());
		System.out.println("model user name is---"+model.getUsername());
		String sjessionid = loginBonita(model.getUsername(), model.getPassword(), model.getServer(), model.getClient());
		if (sjessionid.length() != 0)
		{
			statusCode = sendMessage(payload);
			if (statusCode == 0)
			{
				// Success
			}
		}
		
		return Response.status(model.getResponseCode()).entity(model.getResult()).build();
	}  // End of sendMessageImpl

	
	public Response getOrderDetailsImpl(String orderNumber, String orderVersion) 
	{
		int statusCode = 200;
		String tmp = "{"+
	"\"projectId\": \"TEST ID\","+
	"\"completionTs\": null,"+
	"\"updatedTs\": \"2016-01-5 13:38:07\","+
	"\"updatedBy\": \"V166171\","+
	"\"receivedDate\": \"2016-01-5 13:38:07\","+
	"\"orderNotes\": [{"+
	"	\"createdBy\": \"V166171\","+
	"	\"createdTs\": \"2016-01-5 13:38:07\","+
	"	\"notes\": \"TEST ORDER\""+
	"}],"+
	"\"contacts\": [{"+
	"	\"empId\": \"V166171\","+
	"	\"updatedTs\": \"2016-01-5 13:38:07\","+
	"	\"createdTs\": \"2016-01-5 13:38:07\","+
	"	\"firstName\": \"SENTHIL\","+
	"	\"lastName\": \"KULANDAIVELAN\","+
	"	\"officePhone\": null,"+
	"	\"email\": null,"+
	"	\"contactType\": \"ORDER\","+
	"	\"shortName\": \"SENTHIL\""+
	"}],"+
	"\"orderNumber\": \""+orderNumber+"\","+
	"\"orderType\": \"ADD\","+
	"\"dueDate\": \"2016-01-5 13:38:07\","+
	"\"label\": \"TEST LABLE\","+
	"\"services\": [{"+
	"	\"status\": null,"+
	"	\"updatedBy\": \"V166171\","+
	"	\"updateTs\": \"2016-01-5 13:38:07\","+
	"	\"aSideSiteId\": \"TEST\","+
	"	\"zSideSiteId\": \"TEST\","+
	"	\"facilityType\": \"TEST\","+
	"	\"customerId\": 1,"+
	"	\"prevTrailId\": null,"+
	"	\"networkId\": \"TEST\","+
	"	\"createdTs\": \"2016-01-5 13:38:07\","+
	"	\"createdBy\": \"V166171\","+
	"	\"remarks\": [{"+
	"		\"createdBy\": \"V166171\","+
	"		\"createdTs\": \"2016-01-5 13:38:07\","+
	"		\"notes\": \"TEST\""+
	"	}],"+
	"	\"diversityFlag\": \"Y\","+
	"	\"contacts\": [{"+
	"		\"empId\": \"V166171\","+
	"		\"updatedTs\": \"2016-01-5 13:38:07\","+
	"		\"createdTs\": \"2016-01-5 13:38:07\","+
	"		\"firstName\": \"SENTHIL\","+
	"		\"lastName\": \"KULANDAIVELAN\","+
	"		\"officePhone\": null,"+
	"		\"email\": null,"+
	"		\"contactType\": null,"+
	"		\"shortName\": \"SENTHIL\""+
	"	}],"+
	"	\"trailName\": \"Arpita\","+
	"	\"bandwidth\": \"10G\","+
	"	\"circuitType\": \"WAVE\","+
	"	\"serviceType\": \"DWDM\","+
	"	\"aSideSiteClli\": \"KLLRTX01\","+
	"	\"zSideSiteClli\": \"KLLRTX01\","+
	"	\"configuration\": [{"+
	"		\"ldPop\": \"TEST LD POP\","+
	"		\"swcEthVzNniId\": \"TEST NNI\","+
	"		\"swcClliForNni\": \"TEST CLLI\""+
	"	}],"+
	"	\"serviceVersion\": 1,"+
	"	\"workflowStatus\": \"NEW\","+
	"	\"svcDescriptorName\": \"OM96_DWDM_96_LAMBDA\","+
	"	\"requestedBandwidth\": \"10G\","+
	"	\"garmSensitivity\": 0,"+
	"	\"channalizationScheme\": null,"+
	"	\"applicationDate\": \"2016-01-5 13:38:07\","+
	"	\"scheduledIssueDate\": \"2016-01-5 13:38:07\","+
	"	\"recordIssueDate\": \"2016-01-5 13:38:07\","+
	"	\"designVerifyAssignDate\": \"2016-01-5 13:38:07\","+
	"	\"wiredTestedDate\": \"2016-01-5 13:38:07\","+
	"	\"plannedTestDate\": \"2016-01-5 13:38:07\","+
	"	\"inventoryAvailableDate\": \"2016-01-5 13:38:07\","+
	"	\"aSideSiteState\": \"TEST A LOCATION\","+
	"	\"zSideSiteState\": \"TEST Z LOCATION\","+
	"	\"explicitRouteId\": 1,"+
	"	\"explicitRouteName\": null,"+
	"	\"diversityGroup\": [{"+
	"		\"diversityGroupId\": 0,"+
	"		\"diversityGroupLabel\": null"+
	"	}]"+
	"}],"+
	"\"revisionNumber\": \""+orderVersion+"\""+
	"}";

		return Response.status(statusCode).entity(tmp).build();
	}  // End of getOrderDetailsImpl
	
	
	public Response createProcessImpl(String flowName, String flowVersion, String flowCreatorId, String contract) 
	{
		
		Long processDefinitionId = new Long("0");
		Long createdByUserId = new Long("0");
		Long caseId = new Long("0");

		logger.info("Attempting to create a process instance of flow [" + flowName +
			"] version [" + flowVersion + "] created by user [" + flowCreatorId + 
			"] with contract [" + contract + "].");
		
		model.setProcessName(flowName);
		model.setProcessVersion(flowVersion);
		model.setCreatedByUserName(flowCreatorId);
		
		// Establish API session with Bonita engine
		String sjessionid = loginBonita(model.getUsername(), model.getPassword(), model.getServer(), model.getClient());
	
		if (sjessionid.length() != 0)
		{
			// Get process definition ID from process name and version
			processDefinitionId = getProcessDefinitionId(model.getClient(), flowName, flowVersion); 
			if (processDefinitionId != 0L)
			{	
				// Get list of users authorized to create instance of process
				createdByUserId = getcreatedByUserId(model.getClient(), processDefinitionId, flowCreatorId); 
				if (createdByUserId != 0L)
				{	
					// Create process (needs name, version, id, and user ID who can start it)
					caseId = createProcess(model.getClient(), model.getProcessName(), model.getProcessVersion(), processDefinitionId, createdByUserId, contract);
					if (caseId != 0L)
					{	
						// Success
					}
				}
			}
		}
		else
		{
			model.setResponseCode(401);  // Unauthorized
			model.setStatusCode(-1);
			model.setStatusMessage("User not authorized.");
		}

		// Logout of Bonita
		logoutBonita(model.getServer(), model.getClient()); 
		logger.debug("Create Case Response is::"+model.toString());
		return Response.status(model.getResponseCode()).entity(model.toString()).build();
 	}  // End of createProcessImpl


	public Response createCaseImpl(String flowName, String contract) 
	{
		Long processDefinitionId = new Long("0");
		Long caseId = new Long("0");

		logger.info("Attempting to create a process instance of flow [" + flowName +
			"] with contract [" + contract + "].");
		
		model.setProcessName(flowName);
		
		// Establish API session with Bonita engine
		String sjessionid = loginBonita(model.getUsername(), model.getPassword(), model.getServer(), model.getClient());
		if (sjessionid.length() != 0)
		{
			// Get process definition ID from process name (latest version)
			processDefinitionId = getProcessDefinitionId(model.getClient(), flowName, null); 
			if (processDefinitionId != 0L)
			{	
				// Create case (needs id and contract variables if applicable)
				caseId = createCase(model.getClient(), processDefinitionId, contract);
				if (caseId != 0L)
				{	
					// Success
				}
			}
		}
		else
		{
			model.setResponseCode(401);  // Unauthorized
			model.setStatusCode(-1);
			model.setStatusMessage("User not authorized.");
		}

		// Logout of Bonita
		logoutBonita(model.getServer(), model.getClient());

		return Response.status(model.getResponseCode()).entity(model.toString()).build();
 	}  // End of createCaseImpl

	
	public Response completeUserTaskImpl(Long taskId, String assignee, String contract) 
	{
		// Establish API session with Bonita engine
		Long userId = 0L;
		model.reset();
		String retStatus = "SUCCESS";
		String sjessionid = loginBonita(model.getUsername(),
				model.getPassword(), model.getServer(), model.getClient());
		logger.info("After login to Bonita:");
		if (sjessionid.length() != 0) 
		{
			// user userId by name
			logger.info("Before calling getUserIdByName:");
			userId = getUserIdByName(model.getClient(), assignee);
			logger.info("UserId:" + userId);
			if (userId > 0L) 
			{
				// assign task to the user
				logger.info("Before calling assignHumanTask:");
				retStatus = assignHumanTask(model.getClient(), taskId, userId);
				if (retStatus.equalsIgnoreCase("SUCCESS")) 
				{
					// complete user task
					logger.info("Before calling completeUserTask:");
					retStatus = completeUserTask(model.getClient(), taskId,	contract);
				}
			}

		}
		
		// Logout of Bonita
		logoutBonita(model.getServer(), model.getClient());

		return Response.status(model.getResponseCode())
				.entity(model.toString()).build();
	}  // End of completeUserTaskImpl
	

	private String loginBonita(String userName, String password, String server, HttpClient client)
	{
		String jsessionid = "";
		try
		{
			String encodedURL = server + "bonita/loginservice?username=" + 
				URLEncoder.encode(userName, "UTF-8") + 
				"&password=" + URLEncoder.encode(password, "UTF-8") + 
				"&redirect=false";
			
			GetMethod getM = new GetMethod(encodedURL);
			// logger.fine("Calling GET method for API: " + encodedURL);
			int respCode = client.executeMethod(getM);
			logger.info("Bonita login service API returned with code: " + respCode);
			if (200 == respCode)
			{			
				/*
				// Logging of headers
				Header[] header = getM.getResponseHeaders();
				for (int i=0;i<header.length;i++){
					 logger.fine(header[i].getName() + " : " + header[i].getValue()); 
				}
				*/
				jsessionid = getClientCookieValue(client, "JSESSIONID");
				model.setSessionId(jsessionid);
			}
		} 
		catch(Exception e)
		{
			//logger.severe("Bonita login failed: " + e.getMessage());
			logger.error("Bonita login failed: " + e.getMessage());
			model.setResult("loginBonita Exception: " + e.getMessage());
			model.setSessionId("");
		}
		
		return model.getSessionId();
	}  // End of loginBonita

	
	/**
	Get process definition ID from process name and version
	http://localhost:8080/bonita/API/bpm/process?p=0&c=10&o=displayName%20ASC&f=displayName%3dIvappPool1&f=version%3d1.1
	METHOD: GET
	Returns:
	[{"displayDescription":"","displayName":"IvappPool1","icon":"","description":"","deployedBy":"4",
	"activationState":"ENABLED","version":"1.0","deploymentDate":"2015-12-16 15:10:14.240","name":"IvappPool1",
	"id":"7725181550918481615","configurationState":"RESOLVED","last_update_date":"2015-12-16 15:10:14.700","actorinitiatorid":"3406"}]
	*/
	private Long getProcessDefinitionId(HttpClient client, String flowName, String flowVersion) 
	{
		GetMethod getProcessDefinitionId = null;
		logger.debug("In getProcessDefinitionId0");
		logger.info("In getProcessDefinitionId");
		try
		{	
			String encodedURL = "";
			if (flowVersion != null)
			{
				encodedURL = model.getServer() + "bonita/API/bpm/process?p=0&c=10&o=displayName%20ASC&f=displayName%3d" + 
					URLEncoder.encode(flowName, "UTF-8")+"&f=version%3d" + URLEncoder.encode(flowVersion, "UTF-8");
			}
			else
			{
				encodedURL =  model.getServer() + "bonita/API/bpm/process?p=0&c=10&o=version%20DESC&f=activationState%3dENABLED&f=displayName%3d" + 
					URLEncoder.encode(flowName, "UTF-8");
			}

			getProcessDefinitionId = new GetMethod(encodedURL);
			logger.info("Calling GET method for API: " + encodedURL);
			int respCode = client.executeMethod(getProcessDefinitionId);
			logger.info("Get process definition ID API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				String body = getProcessDefinitionId.getResponseBodyAsString();
				JSONObject obj = new JSONObject("{\"results\": " + body + "}");
				JSONArray arr = obj.getJSONArray("results");
				logger.debug("array process is --"+arr);
				if (arr.length() < 1)
				{
					logger.debug("In getProcessDefinitionId1");
					// Did not find any process to create
					model.setResult("Error: Found " + arr.length() + " processes to use.");
					model.setResponseCode(404);  // Not Found
					model.setStatusCode(-3);
					model.setStatusMessage("No process found.");
				}
				else
				{
					logger.debug("In getProcessDefinitionId2");
					if (flowVersion != null)
					{
						if (arr.length() > 1)
						{
							// Conflict with multiple processes returned when searching by version
							model.setResult("Error: Found " + arr.length() + " processes to use.");
							model.setResponseCode(409);  // Conflict
							model.setStatusCode(-2);
							model.setStatusMessage("Too many processes found.");
						}
						else
						{
							logger.debug("In getProcessDefinitionId3");
							model.setProcessDefinitionId(Long.parseLong(arr.getJSONObject(0).getString("id")));
						}
					}
					else
					{
						logger.debug("In getProcessDefinitionId4");
						// Get process version being used
						model.setProcessVersion(arr.getJSONObject(0).getString("version"));
						// Must get latest flow version (first result when ordered descending by version)
						model.setProcessDefinitionId(Long.parseLong(arr.getJSONObject(0).getString("id")));
					}
				}
			}
			else
			{
				model.setResult(respCode + ":" + getProcessDefinitionId.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("getProcessDefinitionId Error");
			}
		} 
		catch(Exception e)
		{
			//logger.severe("Get process definition failed: " + e.getMessage());
			logger.error("Get process definition failed: " + e.getMessage());
			model.setResult("getProcessDefinitionId Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		}
		finally
		{
			if (getProcessDefinitionId != null)
			{
				getProcessDefinitionId.releaseConnection();
			}
		}
		
		return model.getProcessDefinitionId();
	}  // End of getProcessDefinitionId


	/** Get list of users authorized to create instance of process
	http://localhost:8080/bonita/API/identity/user?c=20&f=enabled=true&f=process_id=7725181550918481615&o=firstname,lastname&p=0
	METHOD: GET
	Results:
	[{"firstname":"Anthony","icon":"/default/icon_user.png","creation_date":"2015-11-16 11:31:41.814",
	"userName":"anthony.nichols","title":"Mr","created_by_user_id":"-1","enabled":"true","lastname":"Nichols",
	"last_connection":"","password":"","manager_id":"17","id":"18","job_title":"Account manager",
	"last_update_date":"2015-11-16 11:31:41.814"},{"firstname":"April","icon":"/default/icon_user.png",
	"creation_date":"2015-11-16 11:31:41.314","userName":"april.sanchez","title":"Mrs","created_by_user_id":"-1",
	"enabled":"true","lastname":"Sanchez","last_connection":"","password":"","manager_id":"3","id":"2",
	"job_title":"Compensation specialist","last_update_date":"2015-11-16 11:31:41.314"}]
	*/
	private Long getcreatedByUserId(HttpClient client, Long processDefinitionId, String createdByUserName) 
	{
		GetMethod getCreatedByUserId = null;
		String searchUserName = "";
		
		try
		{			
			if (createdByUserName != null)
			{
				searchUserName = createdByUserName;
			}
			else
			{
				searchUserName = model.getUsername();
			}

			String encodedURL = model.getServer() + "bonita/API/identity/user?p=0&c=200&f=enabled%20true&f=process_id%20" +
				processDefinitionId.toString()+"&o=firstname,lastname";
			getCreatedByUserId = new GetMethod(encodedURL);
			logger.info("Calling GET method for API: " + encodedURL);
			int respCode = client.executeMethod(getCreatedByUserId);
			logger.info("Get user ID API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				String body = getCreatedByUserId.getResponseBodyAsString();
				JSONObject obj = new JSONObject("{\"results\": " + body + "}");
				JSONArray arr = obj.getJSONArray("results");
				for (int i = 0; i < arr.length(); i++)
				{
					String userName = arr.getJSONObject(i).getString("userName");
					if (userName.equals(searchUserName))
					{
						model.setCreatedByUserId(Long.parseLong(arr.getJSONObject(i).getString("id")));
						break;
					}
				}
				if (model.getCreatedByUserId() <= 0 )
				{
					// Could not find user ID authorized to create process
					model.setResult("Error: User " + searchUserName + " is not authorized to instantiate this process.");
					model.setResponseCode(401);  // Unauthorized
					model.setStatusCode(-4);
					model.setStatusMessage("User not authorized.");
				}
			}
			else
			{
				model.setResult(respCode + ":" + getCreatedByUserId.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("getcreatedByUserId Error");
			}
		} 
		catch(Exception e)
		{
			// logger.severe("Get user ID failed: " + e.getMessage());
			 logger.error("Get user ID failed: " + e.getMessage());
			model.setResult("getcreatedByUserId Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		}
		finally
		{
			if (getCreatedByUserId != null)
			{
				getCreatedByUserId.releaseConnection();
			}
		}
		
		return model.getCreatedByUserId();
	}  // End of getcreatedByUserId

	
	/** Create process (needs name, version, id, and user ID who can start it)
	http://localhost:8080/bonita/portal/resource/process/IvappPool1/1.0/API/bpm/process/7725181550918481615/instantiation?user=4
	METHOD: POST
	Returns:
	{"caseId":33009}
	*/
	private Long createProcess(HttpClient client, String flowName, String flowVersion, Long processDefinitionId, Long createdByUserId, String contract) 
	{
		PostMethod postCreateProcess = null;
		try
		{
			logger.debug(" in Create process");
			String encodedURL = model.getServer() + "bonita/portal/resource/process/" + 
				URLEncoder.encode(flowName, "UTF-8") +"/" + URLEncoder.encode(flowVersion, "UTF-8") + 
				"/API/bpm/process/"+processDefinitionId.toString()+"/instantiation?user="+createdByUserId.toString();
			postCreateProcess = new PostMethod(encodedURL);
			StringRequestEntity requestEntity = new StringRequestEntity(contract, null, null);
			logger.info("Calling POST method for API: " + encodedURL);
			postCreateProcess.setRequestEntity(requestEntity);
			int respCode = client.executeMethod(postCreateProcess);
			logger.info("Create process API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				String body = postCreateProcess.getResponseBodyAsString();
				JSONObject obj = new JSONObject(body);
				model.setCaseId(obj.getLong("caseId"));
				model.setStatusCode(0);
				model.setResponseCode(respCode);
				model.setStatusMessage("Success");
				model.setResult("");
				
			}
			else
			{
				model.setResult(respCode + ":" + postCreateProcess.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("createCase Error");
			}
		} 
		catch(Exception e)
		{
			// logger.severe("Create process instance failed: " + e.getMessage());
			 logger.error("Create process instance failed: " + e.getMessage());
			model.setResult("createProcess Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		}
		finally
		{
			if (postCreateProcess != null)
			{
				postCreateProcess.releaseConnection();
			}
		}
		
		return model.getCaseId();
	}  // End of createProcess

	
	/** Create case
	http://../API/bpm/case
	METHOD: POST
	Request Payload:
 	{  
   		"processDefinitionId":"5777042023671752656",
   		"variables":[  
      	{  
         	"name":"stringVariable",
         	"value":"aValue"
      	},
      	{  
         	"name":"dateVariable",
         	"value":349246800000
      	},
      	{  
         	"name":"numericVariable",
         	"value":5
      	}
   	]
	}
 	Returns:
	{  
   		"id":"1",
   		"end_date":"",
   		"startedBySubstitute": "1",
   		"start":"2013-10-14 12:10:24.996",
   		"state":"started",
   		"rootCaseId": "1",
   		"started_by":"1",
   		"processDefinitionId":"5777042023671752656",
   		"last_update_date":"2013-10-14 12:10:24.996"
	}
	*/
	private Long createCase(HttpClient client, Long processDefinitionId, String contract) 
	{
		// THIS API CALL IS FAILING WHEN A CONTRACT IS PROVIDED.  USE createProcess INSTEAD
		PostMethod postCreateCase = null;
		try
		{			
			String encodedURL = model.getServer() + "bonita/API/bpm/case";
			postCreateCase = new PostMethod(encodedURL);
			
			String casePayload = "";
			
			casePayload = "{ \"processDefinitionId\":\"" + processDefinitionId + "\" }";
			/* COMMENTED WHILE BROKEN
			if (contract != null)
			{
				String caseVariables = "{" +  
						" \"name\" : \"input\", " +
						" \"value\": \"tony\"   " +
					"}";
				casePayload = "{ \"processDefinitionId\":\"" + processDefinitionId + "\", " +
					" \"variables\": [" + caseVariables + "] }";
			}
			else
			{
				casePayload = "{ \"processDefinitionId\":\"" + processDefinitionId + "\" }";
			}
			*/
			
			logger.info("Case payload: " + casePayload);
			StringRequestEntity requestEntity = new StringRequestEntity(casePayload, null, null);
			postCreateCase.setRequestEntity(requestEntity);
			logger.info("Calling POST method for API: " + encodedURL);
			int respCode = client.executeMethod(postCreateCase);
			logger.info("Create case API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				String body = postCreateCase.getResponseBodyAsString();
				JSONObject obj = new JSONObject(body);
				model.setCaseId(obj.getLong("id"));
			}
			else
			{
				model.setResult(respCode + ":" + postCreateCase.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("createCase Error");
			}
		} 
		catch(Exception e)
		{
			// logger.severe("Create case failed: " + e.getMessage());
			 logger.error("Create case failed: " + e.getMessage());
			model.setResult("createCase Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		}
		finally
		{
			postCreateCase.releaseConnection();	
		}
		
		return model.getCaseId();
	}  // End of createCase


	private void logoutBonita(String server, HttpClient client)
	{
		try
		{			
			String encodedURL = server + "bonita/logoutservice?redirect=false";
			GetMethod get = new GetMethod(encodedURL);
			logger.info("Calling GET method for API: " + encodedURL);
			int respCode = client.executeMethod(get);
			logger.info("Bonita logout API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				// Success
			}
		} 
		catch(Exception e)
		{
		//	logger.warning("Bonita logout failed: " + e.getMessage());
		}
		return;
	}  // End of logoutBonita
	
	
	private String getClientCookieValue(HttpClient client, String CookieName)
	{
		String sCookieValue = "";
		Cookie[] cookies = client.getState().getCookies();
		// logger.fine("Bonita login success cookie count: " + cookies.length);
		 logger.info("Bonita login success cookie count: " + cookies.length);
	    for (int i = 0; i < cookies.length; i++)
	    {
	        Cookie cookie = cookies[i];
	         logger.info("Cookie: " + cookie.getName() + ", Value: " + cookie.getValue() +
	          ", IsPersistent?: " + cookie.isPersistent() + ", Expiry Date: " + cookie.getExpiryDate() +
	          ", Comment: " + cookie.getComment());

	        if (cookie.getName().equals(CookieName))
	        {
	        	sCookieValue = cookie.getValue();
	        	break;  
	        }
	    }
	    return sCookieValue;  
	}  // End of getClientCookieValue

	
	
	private int sendMessage(String payload) 
	{
		PostMethod postSendMessage = null;
		try
		{			
			String encodedURL = model.getServer() + "bonita/API/extension/message/receive";
			postSendMessage = new PostMethod(encodedURL);
			StringRequestEntity requestEntity = new StringRequestEntity(payload, null, null);
			postSendMessage.setRequestEntity(requestEntity);
			logger.info("Calling POST method for API: " + encodedURL);
			int respCode = model.getClient().executeMethod(postSendMessage);
			logger.info("Create case API returned with code: " + respCode);
			if (200 == respCode)
			{				 
				String body = postSendMessage.getResponseBodyAsString();
				JSONObject obj = new JSONObject(body);
				model.setStatusCode(obj.getInt("statusCode"));
				model.setResult(body);
				model.setResponseCode(respCode);
				model.setStatusMessage("Success");
			}
			else
			{
				model.setResult(respCode + ":" + postSendMessage.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("sendMessage Error");
			}
		} 
		catch(Exception e)
		{
			 logger.error("Send message failed: " + e.getMessage());
			model.setResult("sendMessage Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		}
		finally
		{
			if (postSendMessage != null)
			{
				postSendMessage.releaseConnection();
			}
		}
		
		return model.getStatusCode();
	}  // End of sendMessage


	
	private String assignHumanTask(HttpClient client, Long taskId, Long userId) 
	{
		PutMethod putAssignTask = null;
		String retStatus = "SUCCESS";
		try 
		{
			String JSONString = "{\"assigned_id\":" + userId + "}";
			String encodedURL = model.getServer() + "bonita/API/bpm/humanTask/" + taskId;
			logger.info("Calling assignHumanTask PUT method for API URL: " + 
					encodedURL + "JSON String:" + JSONString);
			putAssignTask = new PutMethod(encodedURL);
			StringRequestEntity requestEntity = new StringRequestEntity(JSONString, null, null);
			putAssignTask.setRequestEntity(requestEntity);
			int respCode = client.executeMethod(putAssignTask);
			logger.info("Assign human task API returned with code: " + respCode);
			if (200 == respCode) 
			{
				retStatus = "SUCCESS";
			} 
			else 
			{
				model.setResult(respCode + ":" + putAssignTask.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("Error in assigning user to task");
				retStatus = "FAILURE";
			}
		} 
		catch (Exception e) 
		{
			 logger.error("Assign human task failed: " + e.getMessage());
			model.setResult("assign human task Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception in assigning user to task");
			retStatus = "FAILURE";
		} 
		finally 
		{
			if (putAssignTask != null)
			{
				putAssignTask.releaseConnection();
			}
		}

		return retStatus;
	}  // End of assignHumanTask

	
	private Long getUserIdByName(HttpClient client, String userName) 
	{
		GetMethod getUserId = null;
		Long userId = 0L;
		try 
		{
			String encodedURL = model.getServer() + "bonita/API/identity/user?p=0&c=200&s=" + userName;
			logger.info("Calling getUserIdByName GET method for API URL: " + encodedURL);
			getUserId = new GetMethod(encodedURL);
			int respCode = client.executeMethod(getUserId);
			logger.info("Get user ID by name API returned with code: " + respCode);
			if (200 == respCode) 
			{
				String body = getUserId.getResponseBodyAsString();
				JSONObject obj = new JSONObject("{\"results\": " + body + "}");
				JSONArray arr = obj.getJSONArray("results");
				for (int i = 0; i < arr.length(); i++) 
				{
					userId = arr.getJSONObject(i).getLong("id");
				}
				if (userId <= 0L) 
				{
					// Could not find user by Id
					model.setResult("Error: User " + userName + " not found to assign task.");
					model.setResponseCode(401); // Unauthorized
					model.setStatusCode(-4);
					model.setStatusMessage("User not found.");
				}
			} 
			else 
			{
				model.setResult(respCode + ":" + getUserId.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("getUserId Error");
			}
		} 
		catch (Exception e) 
		{
			 logger.error("Get user ID by name failed: " + e.getMessage());
			model.setResult("getUserId Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception.");
		} 
		finally 
		{
			if (getUserId != null)
			{
				getUserId.releaseConnection();
			}
		}

		return userId;
	} // End of getUserIdByName

	
	private String completeUserTask(HttpClient client, Long taskId, String contract) 
	{
		PostMethod postCompleteUserTask = null;
		String retStatus = "SUCCESS";
		try 
		{
			String encodedURL = model.getServer() + "bonita/API/bpm/userTask/" + taskId + "/execution";
			logger.info("Calling completeUserTask POST method for API URL: " + encodedURL);
			postCompleteUserTask = new PostMethod(encodedURL);
			StringRequestEntity requestEntity = new StringRequestEntity(contract, null, null);
			postCompleteUserTask.setRequestEntity(requestEntity);
			int respCode = client.executeMethod(postCompleteUserTask);
			logger.info("Complete user task API returned with code: " + respCode);
			if (200 == respCode || 204 == respCode) 
			{
				retStatus = "SUCCESS";
			} 
			else 
			{
				model.setResult(respCode + ":" + postCompleteUserTask.getResponseBodyAsString());
				model.setResponseCode(respCode);
				model.setStatusCode(-respCode);
				model.setStatusMessage("Error in completing user task");
				retStatus = "FAILURE";
			}
		} 
		catch (Exception e) 
		{
			 logger.error("Complete user task failed: " + e.getMessage());
			model.setResult("complete user task Exception: " + e.getMessage());
			model.setResponseCode(400);
			model.setStatusCode(-4);
			model.setStatusMessage("Exception in completing user to task");
			retStatus = "FAILURE";
		} 
		finally 
		{
			if (postCompleteUserTask != null)
			{
				postCompleteUserTask.releaseConnection();
			}
		}
		return retStatus;
	}  // End of completeUserTask

}  // End of BonitaApiServicesImpl