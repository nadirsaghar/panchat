package xmltojson;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ixmltojson.*;
import org.json.*;
import org.json.zip.*;
import com.google.gson.*;


public class XmlToJson implements IXmlToJson
{
	/*public static void main()
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try 
		{
			builder = factory.newDocumentBuilder();
		} 
		catch (ParserConfigurationException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Document doc = builder.parse("hi");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("hi");
	}*/

	public XmlToJson(String xmlFilePath)
	{
		factory = DocumentBuilderFactory.newInstance();
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			xmlDocument = builder.parse(xmlFilePath);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		xPathFactory = XPathFactory.newInstance();		
		
	}
	
	public String convertXmlToJson(String xml, IMappings mappings,
			ISettings settings) 
	{
		// TODO Auto-generated method stub
		
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(mappings.getMappingsAsString());
		Set<Entry<String,JsonElement>> entrySet = jsonObject.entrySet();
		
		for(Entry e : entrySet)
		{
			String key = (String)e.getKey();
			Object jsonElement =e.getValue();
			
			if(jsonElement instanceof JsonObject)
			{
				JsonObject nestedJsonObject = (JsonObject) jsonElement;
				Set<Entry<String,JsonElement>> entrySetNested = nestedJsonObject.entrySet();
				
				for(Entry eNested : entrySetNested)
				{
					String keyNested = (String)eNested.getKey();
					Object jsonElementNested =eNested.getValue();
					
					if(jsonElementNested instanceof JsonObject)
					{
						JsonObject nestedJsonObject2 = (JsonObject) jsonElementNested;
						Set<Entry<String,JsonElement>> entrySetNested2 = nestedJsonObject2.entrySet();
						for(Entry eNested2 : entrySetNested2)
						{
							String keyNested2 = (String)eNested2.getKey();
							Object jsonElementNested2 =eNested2.getValue();
							
							if(keyNested2.equalsIgnoreCase(keyNested2))
							{
								//String xpath = (String)jsonElementNested2;
								JsonPrimitive jsonPrimitive = (JsonPrimitive)jsonElementNested2;
								String xpath= jsonPrimitive.getAsString();
								
								System.out.println(evaluateXPath(xpath));
							}
						}
				    }
				}
			}
		}
		
		
			
		
		
		 /*
		 JSONObject jsonObject = new JSONObject(mappings.getMappingsAsString());
		 
		 Iterator keys = jsonObject.keys();
		 String key;
		 while(keys.hasNext()) 
		 {
	         key = keys.next().toString();
	         //element
	         Object value = jsonObject.get(key);
	         if(value instanceof JSONObject)
	         	{
	        	 JSONObject nestedValue = (JSONObject)value;
	        	 Iterator nestedKeys = nestedValue.keys();
	        	 while(nestedKeys.hasNext()) 
	    		 {
	    	         String nestedKey = nestedKeys.next().toString();
	    	         String innerValue = nestedValue.getString(nestedKey);
	    	         String hi = "ho";
	    	         
	    		 }
	         	}
	     }*/	 
		 
		 return null;
	}
	
	
    public String evaluateXPath(String xPath)
    {
    	XPath xpath = xPathFactory.newXPath();
    	try {
			XPathExpression expr = xpath.compile(xPath);
			String result = expr.evaluate(xmlDocument);
			return result;
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return null;    
    }
	
	public Boolean validateJson(String jsonSchema, String generatedJson) 
	{
		// TODO Auto-generated method stub
		return null;
	}
    
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document xmlDocument;
	private XPathFactory xPathFactory;
	
	
}
