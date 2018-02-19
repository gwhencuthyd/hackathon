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

/*import com.verizon.accessunification.dao.AccessUnificationDAO;
import com.verizon.accessunification.dto.SaveOrderDTO;
import com.verizon.accessunification.utilities.AUConstants;
import com.verizon.accessunification.utilities.JsonXMLUtil;*/
//import org.apache.commons.httpclient.HttpClient;

@Component
@Path("/bonita")
public class BonitaApiServices 
{
	
	@Autowired
	//public AccessUnificationDAO accessUnificationDAO;
	
	private static final Logger logger= Logger.getLogger(BonitaApiServices.class);
	
	BonitaApiServicesImpl bonitaApiServicesImpl = new BonitaApiServicesImpl();
	

	public BonitaApiServices(){
	    }
	    
	/*public BonitaApiServices(AccessUnificationDAO accessUnificationDAO){
	        this.accessUnificationDAO = accessUnificationDAO;
	    } */
	
	

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
	
	/*@POST
	  @Path("/saveOrderDetails")
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public String saveOrderDetails(String OrderDetails) throws ParseException
	  {   
	  	  
    JSONObject json = new JSONObject();	  
    JSONParser jsonParser = new JSONParser(); 
	JSONObject jsonObject = (JSONObject) jsonParser.parse(OrderDetails);

	 JSONObject nFOrderDetails = (JSONObject) jsonObject.get("nFOrderDetails");
	 
	  SaveOrderDTO saveDTO= new SaveOrderDTO();
	  saveDTO.setCaseId((String)jsonObject.get("caseId"));
	  saveDTO.setnFOrderNum((String)jsonObject.get("nFOrderNum"));
	  saveDTO.setnFOrderVer((String)jsonObject.get("nFOrderVer"));
	  saveDTO.setnFOrderDetails(nFOrderDetails.toString());

		  try {
			    int result = accessUnificationDAO.checkOrderExist(saveDTO.getCaseId());
	          	if(result >0) {
	          		
	          		int updatedRows = accessUnificationDAO.updateOrderDetails(saveDTO);
	          		if (updatedRows != 0) {
	          			json.put(AUConstants.STATUSCODE, AUConstants.STATUSCODE_SUCCESS);
		                json.put(AUConstants.STATUSMESSAGE, AUConstants.SUCCESS);
		             } else {
		            	 json.put(AUConstants.STATUSCODE, AUConstants.STATUSCODE_FAIL);
			                json.put(AUConstants.STATUSMESSAGE, AUConstants.FAILED);
		                 }
	          	} 
	          	else 
	          	{
	      
			  	    int updatedRows = accessUnificationDAO.saveOrderDetails(saveDTO);
			  	    if (updatedRows != 0) {
			  	    	json.put(AUConstants.STATUSCODE, AUConstants.STATUSCODE_SUCCESS);
		                json.put(AUConstants.STATUSMESSAGE, AUConstants.SUCCESS);
		             } else {
		            	 json.put(AUConstants.STATUSCODE, AUConstants.STATUSCODE_FAIL);
			                json.put(AUConstants.STATUSMESSAGE, AUConstants.FAILED);
		                 }
	            }	  		  
	        } catch (Exception e) {
	            logger.error(":::::::::::: Error while saving the order :::::::::::::",e);
	            e.printStackTrace();
	            json.put(AUConstants.STATUSCODE, AUConstants.STATUSCODE_FAIL);
                json.put(AUConstants.STATUSMESSAGE, AUConstants.FAILED);
	        }
		    return json.toString();	        
	    }
	    
	  
	  @GET
	    @Path("/orderDetails/{nfOrderNum}/{nfOrderVer}")
	    public Response orderDetails(@PathParam("nfOrderNum") String NF_ORDER_NUM,
	    										 @PathParam("nfOrderVer") String NF_ORDER_VER) 
		 	{   
		    int statusCode = 200;
		    String result = "";
		    try{
		        if (!NF_ORDER_NUM.isEmpty()) {
		        	result = accessUnificationDAO.getAccessUnificationList(NF_ORDER_NUM, NF_ORDER_VER);
		        	if(result!=null)
		        	return Response.status(statusCode).entity(result).build();
		            else
		            return Response.status(204).entity("Order Details Not Found").build();
		        }
		    }
		    catch (Exception e) {
	            logger.error(":::::::::::: Error while retrieving the order :::::::::::::",e);
	            e.printStackTrace();
	        }
	        return Response.status(400).entity("Invalid Data in the Request").build();
	} 


	    @GET
	    @Path("/orderDetailsXML/{nfOrderNum}/{nfOrderVer}")
	    public Response orderDetailsXML(@PathParam("nfOrderNum") String NF_ORDER_NUM,
												@PathParam("nfOrderVer") String NF_ORDER_VER)  {
			int statusCode = 200;
			String result = null;
			String jsonToXml ="";
			try{
				if (!NF_ORDER_NUM.isEmpty())
					  {
						result = accessUnificationDAO.getAccessUnificationList(
								NF_ORDER_NUM, NF_ORDER_VER);
		
						if (result != null){
						   jsonToXml = JsonXMLUtil.json2Xml(result);
						   jsonToXml = "<nfOrder>"+jsonToXml+"</nfOrder>";
						   jsonToXml = jsonToXml.replaceAll("null","");
						   return Response.status(statusCode).entity(jsonToXml).build();	
						   }			
						else{
							jsonToXml = "<nfOrder>Order Details Not Found</nfOrder>";
							return Response.status(204).entity(jsonToXml).build();
						}
					}
			}
			catch (Exception e) {
	            logger.error(":::::::::::: Error while retrieving the order :::::::::::::",e);
	            e.printStackTrace();
	        }
			return Response.status(400).entity("Invalid Data in the Request")
					.build();
		}	*/
	
}  // End of BonitaApiServices