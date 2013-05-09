package com.auphelia.services;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jettison.json.JSONException;

import com.auphelia.dao.ContactDAO;
import com.auphelia.dao.GoogleDAO;
import com.auphelia.models.Contact;



@Path("/contact")
public class ContactService {
	
	ContactDAO googleDao = new GoogleDAO(); 
	int ctr = 0 ;

	@GET()
	@Produces("application/json; charset=UTF-8")
	public Set<Contact> getAllContact() {	
		return googleDao.getAllContact();
	}
	
	@GET()
	@Path("/{email}")
	@Produces("application/json; charset=UTF-8")
	public Contact getContact(@PathParam("email") String email){
		return googleDao.getContact(email);
	}
	
	@POST
	@Path("/{email}")
	@Consumes()
	@Produces("application/json; charset=UTF-8")
	public Response changeContact(@PathParam("email") String email, String input){
		boolean response;
		try {
			Contact contact = new Contact(input, email);
			response = googleDao.addContact(contact);
			if (response)
				return Response.status(200).entity(contact).build();
			else
				return Response.status(409).entity("Entity already exists").build();
		}
		catch (JSONException exc) {
			return Response.status(404).entity("Error with parsing JSON").build();
		}
	}
	
	
	/*@PUT
	@Path("/{email}")
	@Consumes()
	@Produces("application/json; charset=UTF-8")
	public Response addContact(@PathParam("email") String email, String input){
		boolean response;
		try {
			Contact contact = Contact.newContact(input); 
			response = googleDao.addContact(contact);
			if (response)
				return Response.status(200).entity(contact).build();
			else
				return Response.status(409).entity("Entity already exists").build();
		}
		catch (JSONException exc) {
			exc.printStackTrace();
			return Response.status(404).entity("Error with parsing JSON").build();
		} catch (JsonParseException e) {
			e.printStackTrace();
			return Response.status(404).entity("Error with parsing JSON").build();
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return Response.status(404).entity("Error with parsing JSON").build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(404).entity("Error with parsing JSON").build();
		}
	}
	
	@DELETE
	@Path("/{email}")
	@Consumes()
	@Produces("application/json; charset=UTF-8")
	public Response deleteContact(@PathParam("email") String email){
		boolean response = googleDao.deleteContact(email);
		if (response)
			return Response.status(200).entity("Success").build();
		else
			return Response.status(404).entity("Entity doesn't exist").build();
	}*/
}
