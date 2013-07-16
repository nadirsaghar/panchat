/**
 * 
 */
package org.panchat.xml2json.macros;

import org.xml.sax.*;

import com.google.gson.*;

/**
 * @author nakull
 *
 */
public abstract class AbstractMacro 
{
	private String Name;
	
	private String Description;
		
	public AbstractMacro(String Name, String Description)
	{
		this.Name = Name;
		this.Description = Description;
	}
	
	public abstract String Execute(JsonArray args, InputSource context);
	
}

