package com.auphelia.services;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;

import com.auphelia.dao.ContactDAO;
import com.auphelia.dao.GoogleDAO;
import com.auphelia.models.Contact;

import com.googlecode.objectify.ObjectifyService;
import com.auphelia.jessrules.JessRules ;

@Path("/contact")
public class ContactService {
	
	static{ 
		ObjectifyService.register(Contact.class); 
		}
	
	ContactDAO googleDao = new GoogleDAO(); 
	int ctr = 0 ;

	@GET()
	@Produces("application/json; charset=UTF-8")
	public Set<Contact> httpGetRoot() {	
		return googleDao.getAllContact();
	}
	
	@GET()
	@Path("/{email}")
	@Produces("application/json; charset=UTF-8")
	public Contact httpGetPath(@PathParam("email") String email){
		return googleDao.getContact(email);
	}
	
	@POST()
	@Path("/{email}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response httpPost(@PathParam("email") String email, String input){
		try {
			Contact contact = googleDao.getContact(email);
			if (contact == null) {
				return Response.status(404).entity("Not found").build();
			} else {
				contact = new Contact(input, email);
				googleDao.addContact(contact);
				return Response.status(200).entity(contact).build();
			}
		} catch (JSONException exc) {
			return Response.status(404).entity("Error with JSON parsing").build();
		} catch (Exception e) {
			e.printStackTrace() ;
			return Response.status(404).entity("Error").build();
		}
	}
	
	@PUT()
	@Path("/{email}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response httpPut(@PathParam("email") String email, String input){
		try {
			Contact contact = googleDao.getContact(email);
			if (contact == null) {
				contact = new Contact(input, email);
				JessRules.check(contact);
				googleDao.addContact(contact);
				return Response.status(200).entity(contact).build();
			} else {
				return Response.status(409).entity("Entity already exists !").build();
			}
		} catch (JSONException exc) {
			return Response.status(404).entity("Error with JSON parsing").build();
		} catch (Exception e) {
			e.printStackTrace() ;
			return Response.status(454).entity("Error").build();
		}
	}
	
	
	@DELETE
	@Path("/{email}")
	@Consumes()
	@Produces("text/plain; charset=UTF-8")
	public Response httpDelete(@PathParam("email") String email){
		if (googleDao.getContact(email) == null) {
			return Response.status(404).entity("Entity doesn't exist").build();
		} else {
			googleDao.deleteContact(email);
			return Response.status(200).entity("Xntity deleted").build();
		}
	}
}
