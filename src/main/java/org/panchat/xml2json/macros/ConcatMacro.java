package org.panchat.xml2json.macros;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.*;

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


	public JsonElement execute(JsonArray macroArguments , Document context) throws MacroExeception 
	{		
		String result = "";
		XPathFactory xPathFactory;
		xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
    	try 
    	{   for(int i =0; i<macroArguments.size();i++)
    		{
    			JsonElement value = macroArguments.get(i);
    			if(value.isJsonObject())
    			{
    				if( ((JsonObject)value).has("xpath") )
    				{
    					XPathExpression expr = xpath.compile( ((JsonObject)value).getAsJsonPrimitive("xpath").getAsString()  );
    					result += expr.evaluate(context);
    				}
    				else if( ((JsonObject)value).has("string") )
    				{
    					result += ((JsonObject)value).getAsJsonPrimitive("string").getAsString();
    				}
    			}	    		
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
