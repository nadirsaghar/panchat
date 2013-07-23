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

public class ConcatMacro implements IMacro {

	public String getName() {
		return "concat";
	}

	public String getDescription() {

		return "macro to concatenate string values";
	}

	public String getAuthor() {

		return "nadir";
	}


	public JsonElement execute(JsonArray macroArguments , Document context) throws MacroExeception {
		/** Implement concatenation here **/
		
		String result = "";
		XPathFactory xPathFactory;
		xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
    	try 
    	{   for(int i =0; i<macroArguments.size();i++)
    		{
    			JsonElement value = macroArguments.get(i);    			
	    		XPathExpression expr = xpath.compile(value.getAsString());
				result += expr.evaluate(context);
    		}
									
		} 
    	catch (XPathExpressionException e) 
		{			
			e.printStackTrace();
		}    	
		return new JsonPrimitive(result);  

		
		//return null;
	}

}
