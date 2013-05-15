package com.auphelia.services;

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

import org.codehaus.jettison.json.JSONObject;

import com.auphelia.dao.ContactDAO;
import com.auphelia.dao.GoogleDAO;
import com.auphelia.models.Contact;
import com.auphelia.rules.ContactRules;
import com.googlecode.objectify.ObjectifyService;

@Path("/contact")
public class ContactService {
	
	static{ 
		ObjectifyService.register(Contact.class); 
		}
	
	ContactDAO contactDao = new GoogleDAO(); 
	int ctr = 0 ;

	@GET()
	@Produces("application/json; charset=UTF-8")
	public Set<Contact> httpGetRoot() {	
		return contactDao.getAllContact();
	}
	
	@GET()
	@Path("/{email}")
	@Produces("application/json; charset=UTF-8")
	public Contact httpGetPath(@PathParam("email") String email){
		return contactDao.getContact(email);
	}
	
	@POST
	@Path("/{email}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response changeContact( @PathParam("email") String email, Contact contact){
		boolean response;
		try {
			JSONObject msg = ContactRules.check(contact);
			if (msg.length() == 0){
				response = contactDao.changeContact(contact);
				if (response)
					return Response.status(200).entity(msg).build();
				else
					return Response.status(404).entity("Entity not found").build();
			}
			else
				return Response.status(422).entity(msg).build();
		}
		catch (Exception e) {
			return Response.status(415).entity(e.getMessage()).build();
		} 
	}
	
	@PUT
	@Path("/{email}")
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	public Response addContact(@PathParam("email") String email, Contact contact){
		boolean response;
		try {
			JSONObject msg = ContactRules.check(contact);
			if (msg.length() == 0){
				response = contactDao.addContact(contact);
				if (response)
					return Response.status(201).entity(msg).build();
				else
					return Response.status(409).entity("Entity already exists").build();
			}
			else
				return Response.status(422).entity(msg).build();
		} catch (Exception e) {
			return Response.status(415).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{email}")
	@Consumes()
	@Produces("text/plain; charset=UTF-8")
	public Response deleteContact(@PathParam("email") String email){
		boolean response = contactDao.deleteContact(email);
		if (response)
			return Response.status(204).build();
		else
			return Response.status(404).entity("Entity doesn't exist").build();
	}
}
