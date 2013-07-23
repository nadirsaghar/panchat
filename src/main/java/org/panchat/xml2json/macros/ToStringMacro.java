/**
 * 
 */
package org.panchat.xml2json.macros;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * @author nakull
 *
 */
public class ToStringMacro implements IMacro {

	public String getName() {
		return "toString";
	}

	public String getDescription() {

		return "macro to add value as String";
	}

	public String getAuthor() {

		return "nakull";
	}


	public JsonElement execute(JsonArray macroArguments , Document context) throws MacroExeception {
		
		
		String result = "";
		
		for(int i =0; i<macroArguments.size();i++)
		{
			JsonElement value = macroArguments.get(i);	    		
			result += value.getAsString();
		}		
    	    	
		return new JsonPrimitive(result);  

		
		//return null;
	}

}
