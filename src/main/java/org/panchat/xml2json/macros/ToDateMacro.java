package org.panchat.xml2json.macros;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ToDateMacro implements IMacro {

	public String getName() {
		return "toDate";
	}

	public String getDescription() {

		return "Macro to convert sting (in specified format) to Date (in specified format).";
	}

	public String getAuthor() {

		return "nakull";
	}


	public JsonElement execute(JsonArray macroArguments , Document context , XPath xPathEvaluator) throws MacroExeception 
	{		
		String result = "", date ="", sourceFormat ="", desiredFormat = "";
		
		/*XPathFactory xPathFactory;
		xPathFactory = XPathFactory.newInstance();
		XPath xpath = xPathFactory.newXPath();*/
		
		JsonElement value = macroArguments.get(0);
		if(value.isJsonObject())
		{
			if( ((JsonObject)value).has("xpath") )
			{				 
				 try 
				 {
					XPathExpression expr = xPathEvaluator.compile( ((JsonObject)value).getAsJsonPrimitive("xpath").getAsString()  );
					date = expr.evaluate(context);
				 } 
				 catch (XPathExpressionException e) 
				 {					
					e.printStackTrace();
				 }				 
			}
			else if( ((JsonObject)value).has("setAsCurrentDate") && 
					((JsonObject)value).getAsJsonPrimitive("xpath").getAsBoolean() == true )
			{
				 Date d = new Date();
				 date = d.toString();
			}
				 
			if( ((JsonObject)value).has("sourceFormat") )
			{
				 sourceFormat = ((JsonObject)value).getAsJsonPrimitive("sourceFormat").getAsString();
			}
			else
				sourceFormat ="MMMM dd, yyyy";
			
			if( ((JsonObject)value).has("desiredFormat") )
			{
				 desiredFormat = ((JsonObject)value).getAsJsonPrimitive("desiredFormat").getAsString();
			}
			else
				 desiredFormat = "MMMM dd, yyyy";
			
			SimpleDateFormat originalFormat = new SimpleDateFormat(sourceFormat);
			SimpleDateFormat targetFormat = new SimpleDateFormat(desiredFormat);
			try 
			{
				result = targetFormat.format(originalFormat.parse(date));
			} 
			catch (ParseException e) 
			{
				return null;
			}			
			
		}
		
		
		return new JsonPrimitive(result);
	}


}
