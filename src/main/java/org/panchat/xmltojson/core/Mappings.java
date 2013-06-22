/**
 * 
 */
package org.panchat.xmltojson.core;

import org.panchat.xmltojson.interfaces.IMappings;

/**
 * @author nakull
 *
 */
public class Mappings implements IMappings {
	
	public String getMappingsAsString() {
		
		return jsonSchema;
	}
	
	public Mappings(String mappings)
	{
		jsonSchema = mappings;
	}
	
	private String jsonSchema;

}
