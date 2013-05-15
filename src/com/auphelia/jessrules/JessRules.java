package com.auphelia.jessrules;

import com.auphelia.dao.ContactDAO;
import com.auphelia.models.Contact;

import jess.* ;

public class JessRules {
    public static void check(Contact contact) throws JessException {
    	//WorkingMemoryMarker marker;
    	Rete engine = new Rete(); // Create a Jess rule engine
        //engine.reset();
        engine.batch("com/auphelia/jessrules/jessrules.clp"); // Load the pricing rules
        engine.add(contact);
        // engine.addAll() ;
        // marker = engine.mark();  // Mark end of catalog data for later
        //engine.resetToMark(marker); // Remove any previous order data, leaving only catalog data
    	//loadOrderData(orderNumber); // Load data for this order
        engine.run(); // Fire the rules that apply to this order
        //return engine.getObjects(new Filter.ByClass(Offer.class)); // Return the list of offers created by the rules
    }
    }


