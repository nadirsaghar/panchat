/**
 * 
 */
package org.panchat.xml2json.core;

import org.panchat.xml2json.api.IMappings;
import org.panchat.xml2json.api.ISettings;
import org.panchat.xml2json.api.IXml2JSON;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import com.google.gson.*;

/**
 * @author nakull
 *
 */
public class Xml2JSON implements IXml2JSON {

	
	public Xml2JSON(String xmlFilePath)
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
	
	/* (non-Javadoc)
	 * @see org.panchat.xml2json.interfaces.IXml2Json#convertXmlToJson(java.lang.String, org.panchat.xml2json.interfaces.IMappings, org.panchat.xml2json.interfaces.ISettings)
	 */
	public String convertXmlToJson(String xml, IMappings mappings,
			ISettings settings) {
		JsonObject generatedJson = new JsonObject();
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = (JsonObject)parser.parse(mappings.getMappingsAsString());
		Set<Entry<String,JsonElement>> entrySet = jsonObject.entrySet();
		
		for(Entry<String,JsonElement> e : entrySet)
		{
			String key = (String)e.getKey();
			JsonElement jsonElement =e.getValue();
			
			// Should go inside for the "properties" key
			if(jsonElement instanceof JsonObject)
			{
				JsonObject propertiesObject = (JsonObject) jsonElement;
				
				//Get each nested JsonObject inside Properties in the Set
				Set<Entry<String,JsonElement>> propertySet = propertiesObject.entrySet();
				
				//Iterate through each property
				for(Entry<String,JsonElement> property : propertySet)
				{
					String propertyName = property.getKey();
					JsonElement propertyValue =property.getValue();
					
					// Should be JsonObject - else ignore
					if(propertyValue instanceof JsonObject)
					{
						JsonObject propertyValueObject = (JsonObject) propertyValue;
						Set<Entry<String,JsonElement>> propertyInternals = propertyValueObject.entrySet();
						
						// Get value and type for each property
						String propertyType = null, xPath = null;	
						
						for(Entry<String,JsonElement> propertyInternal : propertyInternals)
						{							
							String propertyInternalKey = propertyInternal.getKey();
							JsonElement propertyInternalValue = propertyInternal.getValue();
							
							if(propertyInternalKey.equalsIgnoreCase("description"))
							{								
								if(propertyInternalValue instanceof JsonPrimitive && ((JsonPrimitive) propertyInternalValue).isString())
								{
									JsonPrimitive jsonPrimitive = (JsonPrimitive)propertyInternalValue;
									xPath = jsonPrimitive.getAsString();
								}							
									
							}
							else if(propertyInternalKey.equalsIgnoreCase("type"))
							{
								if(propertyInternalValue instanceof JsonPrimitive && ((JsonPrimitive) propertyInternalValue).isString())
								{
									JsonPrimitive jsonPrimitive = (JsonPrimitive)propertyInternalValue;
									propertyType = jsonPrimitive.getAsString();
								}
							}
							
						}
						
						//TO DO : modify evaluateXPath to take in type
						if(propertyType.equalsIgnoreCase("string"))
						{
							generatedJson.addProperty(propertyName, evaluateXPath(xPath));
						}
						else if (propertyType.equalsIgnoreCase("number"))
						{
							generatedJson.addProperty(propertyName, evaluateXPath(xPath));
						}
						else if (propertyType.equalsIgnoreCase("array"))
						{
							JsonArray computedValue = computeArrayValue(propertyValueObject);
							generatedJson.add(propertyName, computedValue);
						}
						
				    }
				}
			}
		}
		 
		return generatedJson.toString();
	}
	
	private JsonArray computeArrayValue(JsonObject schemaNode)
	{
		
		return null;
	}

	private String evaluateXPath(String xPath)
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
	/* (non-Javadoc)
	 * @see org.panchat.xml2json.interfaces.IXml2Json#validateJson(java.lang.String, java.lang.String)
	 */
	public Boolean validateJson(String jsonSchema, String generatedJson) {
		// TODO Auto-generated method stub
		return null;
	}

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document xmlDocument;
	private XPathFactory xPathFactory;
	
}
