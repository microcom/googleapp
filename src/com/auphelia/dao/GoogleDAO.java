package com.auphelia.dao;

import java.util.Set;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.auphelia.models.Contact;

public class GoogleDAO implements ContactDAO {

	private DatastoreService datastore ;
	
	public GoogleDAO (){
		 datastore = DatastoreServiceFactory.getDatastoreService();
	}
	
	
	public Set<Contact> getAllContact() {
		

		return null;
	}

	public Contact getContact(String email) {


		return null;
	}

	public boolean addContact(Contact contact) {
		Entity newContact = new Entity("Contact");
		
		return false;
	}

	public boolean deleteContact(String email) {


		return false;
	}

}
