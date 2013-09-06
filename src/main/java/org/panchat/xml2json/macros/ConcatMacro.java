package org.panchat.xml2json.macros;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ConcatMacro implements IMacro {

	public String getName() {
		return "concat";
	}

	public String getDescription() {

		return "Macro to concatenate string values - both xpath evaluated values and hard coded strings. ";
	}

	public String getAuthor() {

		return "nadir";
	}


	public JsonElement execute(JsonArray macroArguments , Document context , XPath xPathEvaluator) throws MacroExeception 
	{		
		String result = "";
		/*XPathFactory xPathFactory;
		xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();
		xpath.setNamespaceContext(new UniversalNamespaceCache(xmlDocument,false));*/
    	try 
    	{   for(int i =0; i<macroArguments.size();i++)
    		{
    			JsonElement value = macroArguments.get(i);
    			if(value.isJsonObject())
    			{
    				if( ((JsonObject)value).has("xpath") )
    				{
    					XPathExpression expr = xPathEvaluator.compile( ((JsonObject)value).getAsJsonPrimitive("xpath").getAsString()  );
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
	}

}
