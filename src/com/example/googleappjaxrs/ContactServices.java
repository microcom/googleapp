package com.example.googleappjaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/*import javax.ws.rs.PathParam;

import java.util.List;
import java.util.ArrayList;
 
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;*/
 
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/*import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity; */

import com.example.contactclass.Contact;

@Path("/time")
public class ContactServices {

	private Contact LocalContact = new Contact("Leo", "Ferre", "Moncton", "Qc" , "PQ" , "HoHoHo") ;
	
	/*@PUT()
	@Produces("text/plain")
	public String getData(@PathParam("requested_data") String requested_data) {
		mpaList.add(requested_data) ;
		return requested_data ;	
	}*/
	
	@PUT
    @Consumes()
    public Response putContact(String arg) {
		LocalContact.setCodePostal(arg) ;
		ResponseBuilder builder = Response.ok(LocalContact);
		builder.language("fr").header("Access-Control-Allow-Origin", "*");
		return builder.build();
    }
	
	@GET()
	@Produces({"application/json"})
	public Response getContact() {
		ResponseBuilder builder = Response.ok(LocalContact);
		builder.language("fr").header("Access-Control-Allow-Origin", "*");
		return builder.build();
		}
		
    }










