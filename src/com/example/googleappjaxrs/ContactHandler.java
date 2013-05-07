package com.example.googleappjaxrs;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.core.Application;

public class ContactHandler extends Application {

	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public ContactHandler(){
	     singletons.add(new ContactServices());
	}
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
}
