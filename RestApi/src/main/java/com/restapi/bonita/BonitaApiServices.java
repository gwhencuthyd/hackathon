package com.restapi.bonita;



import javax.ws.rs.GET;
//import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
//import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;



import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;   




import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
@Path("/bonita")
public class BonitaApiServices 
{
	
	@Autowired
	
	
	private static final Logger logger= Logger.getLogger(BonitaApiServices.class);
	
	BonitaApiServicesImpl bonitaApiServicesImpl = new BonitaApiServicesImpl();
	

	public BonitaApiServices(){
	    }
	    
	
	
	

	@GET
	@Path("/testConnection")
	public Response testConnectionGetService() 
	{
		return Response.status(200).entity("{ \"statusCode\": 0, \"statusMessage\": \"Connection successful\" }").build();
 	}  // End of testConnectionService
	@POST
	@Path("/testConnection")
	public Response testConnectionPostService() 
	{
		return Response.status(200).entity("{ \"statusCode\": 0, \"statusMessage\": \"Connection successful\" }").build();
 	}  // End of testConnectionService


	@POST
	@Path("/send/message")
	public Response sendMessageService(String payload) 
	{
		return bonitaApiServicesImpl.sendMessageImpl(payload);
 	}  // End of createCaseService
	

//	@GET
//	@Path("/orderDetails/{orderNumber}/{orderVersion}")
//	public Response getOrderDetails(
//			@PathParam("orderNumber") String orderNumber, 
//			@PathParam("orderVersion") String orderVersion)
//	{
//		return bonitaApiServicesImpl.getOrderDetailsImpl(orderNumber, orderVersion);
// 	}  // End of createCaseService

	
	@POST
	@Path("/createProcess/{processName}/{processVersion}/{createdByUserName}")
	public Response createProcessService(
			@PathParam("processName") String processName,
			@PathParam("processVersion") String processVersion,
			@PathParam("createdByUserName") String createdByUserName,
			String contract)
	{
		return bonitaApiServicesImpl.createProcessImpl(processName, processVersion, createdByUserName, contract);
 	}  // End of createCaseService

	
	@POST
	@Path("/createCase/{processName}")
	public Response createCaseService(
			@PathParam("processName") String processName, 
			String contract) 
	{
		//return bonitaApiServicesImpl.createCaseImpl(processName, contract);
		return bonitaApiServicesImpl.createProcessImpl(processName, null, null, contract);
 	}  // End of createCaseService
	@POST
	@Path("/createCase/{processName}/{createdByUserName}")
	public Response createCaseService(
			@PathParam("processName") String processName,
			@PathParam("createdByUserName") String createdByUserName,
			String contract) 
	{
		//return bonitaApiServicesImpl.createCaseImpl(processName, contract);
		return bonitaApiServicesImpl.createProcessImpl(processName, null, createdByUserName, contract);
 	}  // End of createCaseService
	
	
	@POST
	@Path("/assignTask/{taskId}/{assignToUserName}")
	public Response assignTaskService(
			@PathParam("taskId") Long taskId, 
			@PathParam("assignToUserName") String assignToUserName) 
	{
		// Not currently implemented
		return Response.status(501).entity("").build();
 	} // End of assignTaskService
	@POST
	@Path("/assignTask/{caseId}/{taskName}/{assignToUserName}")
	public Response assignTaskService(
			@PathParam("caseId") Long caseId,
			@PathParam("taskName") String taskName,
			@PathParam("assignToUserName") String assignToUserName) 
	{
		// Not currently implemented
		return Response.status(501).entity("").build();
 	} // End of assignTaskService
	
	
	@POST
	@Path("/unassignTask/{taskId}")
	public Response unassignTaskService(
			@PathParam("taskId") Long taskId) 
	{
		// Not currently implemented
		return Response.status(501).entity("").build();
 	}  // End of unassignTaskService
	@POST
	@Path("/unassignTask/{caseId}/{taskName}")
	public Response unassignTaskService(
			@PathParam("caseId") Long caseId,
			@PathParam("taskName") String taskName)
	{
		// Not currently implemented
		return Response.status(501).entity("").build();
 	}  // End of unassignTaskService

	
	@POST
	@Path("/completeTask/{taskId}/{completedByUserName}")
	public Response completeTaskByIdService(@PathParam("taskId") Long taskId,
			@PathParam("completedByUserName") String completedByUserName,
			String contract) 
	{
		return bonitaApiServicesImpl.completeUserTaskImpl(taskId,
				completedByUserName, contract);
	} // End of completeTaskByIdService
	
	
	
}  // End of BonitaApiServices