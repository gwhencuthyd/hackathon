package com.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("api")

public class MyRestAPI {
	@GET
	@Path("testGet")
	//@Consumes(MediaType.APPLICATION_JSON)
	public Response testGet(){
		return Response.status(200).entity("Success").build();
	}
	
	@POST
	@Path("employee")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee getEmployee(Employee employee){
		employee.setName("Jai Ganesh");
		
		employee.setDepartment("IT");
		//JSONObject obj = new JSONObject(employee);
		
		return employee;
	}
	
	@POST
	@Path("testDate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Employee testDate(Employee employee){
		employee.setName("Jai Ganesh");
		
		employee.setDepartment("IT");
		//JSONObject obj = new JSONObject(employee);
		
		return employee;
	}
	
}
