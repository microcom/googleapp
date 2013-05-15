package com.auphelia.rules;

import jess.JessException;
import jess.Rete;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.auphelia.models.Contact;

public class ContactRules {
    public static JSONObject check(Contact contact) throws JSONException {
    	JSONObject msg = new JSONObject();
		try {
	    	//WorkingMemoryMarker marker;
	    	Rete engine = new Rete(); // Create a Jess rule engine
	        //engine.reset();
	        engine.batch("com/auphelia/rules/contactrules.clp"); // Load the pricing rules
	        engine.add(contact);
	        engine.add(msg);
	        // engine.addAll() ;
	        // marker = engine.mark();  // Mark end of catalog data for later
	        // engine.resetToMark(marker); // Remove any previous order data, leaving only catalog data
	    	// loadOrderData(orderNumber); // Load data for this order
	        engine.run(); // Fire the rules that apply to this order
	        // return engine.getObjects(new Filter.ByClass(Offer.class)); // Return the list of offers created by the rules
	        return(msg) ;
    	} catch (JessException e){
    		msg.accumulate("error", e.getDetail());
    		return(msg) ;
    	} 
    }
}


