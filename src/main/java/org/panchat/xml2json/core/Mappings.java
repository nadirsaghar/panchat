/**
 * 
 */
package org.panchat.xml2json.core;

import org.panchat.xml2json.interfaces.IMappings;

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
