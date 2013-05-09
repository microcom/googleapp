package com.auphelia.dao;

import java.util.Set;
import com.auphelia.models.Contact;

public interface ContactDAO {
	
	public Set<Contact> getAllContact();

	public Contact getContact(String email);
	
	public boolean addContact(Contact contact);
	
	public boolean deleteContact(String email);
}
