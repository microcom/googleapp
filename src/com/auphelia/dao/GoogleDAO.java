package com.auphelia.dao;

import com.auphelia.models.Contact;
import com.googlecode.objectify.cmd.Query;

import java.util.HashSet;
import java.util.Set;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class GoogleDAO implements ContactDAO {
	public Set<Contact> getAllContact() {
		Set<Contact> returnedContacts = new HashSet<Contact>();
		Query<Contact> fetchedContacts = ofy().load().type(Contact.class);
		for (Contact insertedContact : fetchedContacts ){
			returnedContacts.add(insertedContact) ;
		}
		return returnedContacts;
	}

	public Contact getContact(String email) {
		Contact fetchedContact = ofy().load().type(Contact.class).id(email).now() ;
		return fetchedContact;
	}

	public boolean addContact(Contact contact) {
		System.out.println(contact.getEmail()) ;
		Contact fetchedContact = ofy().load().type(Contact.class).id(contact.getEmail()).now() ;
		if (fetchedContact == null) {
			ofy().save().entity(contact).now();
			return true ;
			}
		else {
			return false ;
		}
	}

	public boolean deleteContact(String email) {
		ofy().delete().type(Contact.class).id(email).now() ;
		return true;
	}

	public boolean changeContact(Contact contact) {
		Contact fetchedContact = ofy().load().type(Contact.class).id(contact.getEmail()).now() ;
		if (fetchedContact == null) {
			return false ;
		} else {
			ofy().save().entity(contact).now();
			return true ;
		}
	}
}
