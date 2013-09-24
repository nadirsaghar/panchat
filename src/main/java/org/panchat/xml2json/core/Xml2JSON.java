/**
 * 
 */
package org.panchat.xml2json.core;

import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.panchat.xml2json.api.IMappings;
import org.panchat.xml2json.api.ISettings;
import org.panchat.xml2json.api.IXml2JSON;
import org.panchat.xml2json.conf.Configuration;
import org.panchat.xml2json.conf.MyLogger;
import org.panchat.xml2json.exception.MacroExeception;
import org.panchat.xml2json.exception.MacroNotFoundException;
import org.panchat.xml2json.macros.IMacro;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * @author nakull
 * 
 * Core Class for Conversion
 *
 */
public class Xml2JSON implements IXml2JSON 
{
	private Configuration configuration;
	
	private final static Logger LOGGER = MyLogger.getLogger();
	
	public Xml2JSON() throws ParserConfigurationException
	{		
		factory = DocumentBuilderFactory.newInstance();		
		
		factory.setNamespaceAware(true);
		
		builder = factory.newDocumentBuilder();		
				
		configuration = new Configuration();
		
		xPath = XPathFactory.newInstance().newXPath();    
		
	}

	public Configuration getConfigurationObject()
	{
		return configuration;
	}
	
	public String convertXmlToJson(String XMLFilePath, IMappings mappings,ISettings settings) 
	{
		try {
			xmlDocument = builder.parse(XMLFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		xPath.setNamespaceContext(new UniversalNamespaceCache(xmlDocument,false));
		
		LOGGER.info("Starting Conversion");
		JsonParser parser = new JsonParser();
		JsonObject mappingsObject = (JsonObject)parser.parse(mappings.getMappingsAsString());
		
		if(!mappingsObject.has("properties"))
		{
			LOGGER.severe("The Schema file is Empty - has no properties!!");
			return null;
		}
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(computeObjectValue(mappingsObject.getAsJsonObject("properties"), xmlDocument));		
	}
	
	private JsonElement executeMacro(String name, JsonArray args, Document context) throws MacroNotFoundException, MacroExeception
	{
		IMacro macro = configuration.lookupMacroByName(name);
		
		if(macro == null) 
			throw new MacroNotFoundException(name);
		
		return macro.execute(args , context , xPath);
	}
	
	private JsonObject computeObjectValue(JsonObject schemaNode, Node context)
	{
		JsonObject computedObject = new JsonObject();
		JsonObject propertiesObject = schemaNode;
		
		//Get each nested JsonObject inside Properties in the Set
		Set<Entry<String,JsonElement>> propertySet = propertiesObject.entrySet();
				
		//Iterate through each property
		for(Entry<String,JsonElement> property : propertySet)
		{
			String propertyName = property.getKey();
			JsonElement propertyValue =property.getValue();			
			computedObject.add(propertyName, computeJsonElement(propertyValue, propertyName, context));			
		}
		return computedObject;
	}
	
	private JsonElement computeJsonElement(JsonElement propertyValue, String propertyName, Node context)
	{
		// Should be JsonObject - else ignore
		if(!(propertyValue instanceof JsonObject))
		{
			return null;
		}
		
		JsonObject propertyValueObject = (JsonObject) propertyValue;
					
		// Get value and type for each property
		String propertyType = "", xPath = "", defaultValue = "";
		
		if(propertyValueObject.has("xpath"))
		{
			JsonPrimitive xpathPrimitive = propertyValueObject.getAsJsonPrimitive("xpath");
			if(xpathPrimitive.isString())
				xPath = xpathPrimitive.getAsString();
		}
		
		if(propertyValueObject.has("type"))
		{
			JsonPrimitive typePrimitive = propertyValueObject.getAsJsonPrimitive("type");
			if(typePrimitive.isString())
				propertyType = typePrimitive.getAsString();
		}
		
		if(propertyValueObject.has("default"))
		{
			JsonPrimitive defaultPrimitive = propertyValueObject.getAsJsonPrimitive("default");
			if(defaultPrimitive.isString())
				defaultValue = defaultPrimitive.getAsString();
		}
					
		if(propertyValueObject.has("macro"))
		{
			JsonObject macroValue = propertyValueObject.getAsJsonObject("macro");
						
			try 
			{
				LOGGER.info("Using macro " + macroValue.getAsJsonPrimitive("name").getAsString());
				 
				return executeMacro(macroValue.getAsJsonPrimitive("name").getAsString(),macroValue.getAsJsonArray("arguments"),xmlDocument);
											
			}
			catch(Exception ee)
			{
				ee.printStackTrace();
			}
		}
		
		//TO DO : modify evaluateXPath to take in type
		else if(propertyType.equalsIgnoreCase("string"))
		{
			String value = evaluateXPath(xPath, context);
			if(value.isEmpty() && !defaultValue.isEmpty())
			{				
				LOGGER.info(propertyName + " 's xpath expression evaluates to null so using default value");
				return new JsonPrimitive(defaultValue);
			}
			else if(value != null)
			{
				LOGGER.info(propertyName + " has a primitive value - adding");
				return new JsonPrimitive(value);
			}
		}
		else if (propertyType.equalsIgnoreCase("number"))
		{
			String value = evaluateXPath(xPath,context);
			if(value.isEmpty() && !defaultValue.isEmpty())
			{				
				LOGGER.info(propertyName + " 's xpath expression evaluates to null so using default value");
				return new JsonPrimitive(defaultValue);
			}
			else if(value != null)
			{
				return new JsonPrimitive(value);
			}
		}
		else if (propertyType.equalsIgnoreCase("array"))
		{
			LOGGER.info(propertyName + " has an array value : computing");				
			JsonArray computedValue = computeArrayValue(propertyValueObject, context);
			return computedValue;
		}
		else if (propertyType.equalsIgnoreCase("object"))
		{
			LOGGER.info(propertyName + " has an array value : computing");				
			JsonObject computedValue = computeObjectValue(propertyValueObject, context);
			return computedValue;
		}
        LOGGER.info(propertyName + "'s value cannot be evaluated !!");
		return null;
	}
	
	private JsonArray computeArrayValue(JsonObject schemaNode, Node context)
	{		
		//Get each nested JsonObject inside Properties in the Set
		JsonArray generatedArray = new JsonArray();
		Set<Entry<String,JsonElement>> propertySet = schemaNode.entrySet();
		
		String xPathParent = null;
		JsonObject properties = null;
		Boolean primitiveArray = false;  
		//Iterate through each property		
		
		for(Entry<String,JsonElement> property : propertySet)
		{
			String propertyName = property.getKey();
			if(propertyName.equalsIgnoreCase("xpath"))
			{
				if(property.getValue() instanceof JsonPrimitive && ((JsonPrimitive) property.getValue()).isString())
				{
					JsonPrimitive jsonPrimitive = (JsonPrimitive)property.getValue();
					xPathParent = jsonPrimitive.getAsString();
					//break;
				}	 
			}
			else if(propertyName.equalsIgnoreCase("properties"))
			{
				properties = (JsonObject) property.getValue();				
			}
			else if((propertyName.equalsIgnoreCase("isPrimitive")))
			{
				JsonPrimitive jsonPrimitive = (JsonPrimitive)property.getValue();
				if(jsonPrimitive.getAsString().equalsIgnoreCase("true"))
				{
					primitiveArray = true;
				}
			}
			
		}
		
		if(primitiveArray)
		{
			NodeList nodeList = evaluateXPathNodeSet(xPathParent);
			for(int i=0;i<nodeList.getLength();i++)
			{
				Node currentNode = nodeList.item(i);
				JsonPrimitive arrayElement = new JsonPrimitive(currentNode.getNodeValue());
				generatedArray.add(arrayElement);
			}
			return generatedArray;
		}
		
		Set<Entry<String,JsonElement>> propertySetNested = properties.entrySet();
		
		
		NodeList nodeList = evaluateXPathNodeSet(xPathParent);
		
		for (int i=0;i<nodeList.getLength();i++)
		{
			Node currentNode = nodeList.item(i);
			JsonObject arrayElement = new JsonObject();
			for(Entry<String,JsonElement> property : propertySetNested)
			{
				
				String propertyName = property.getKey();
				JsonElement propertyValue =property.getValue();
				arrayElement.add(propertyName, computeJsonElement(propertyValue, propertyName, currentNode));
			}
			generatedArray.add(arrayElement);
		}			
		return generatedArray;
	}

	private String evaluateXPath(String xPathExpression,Node context)
    {    	
    	try 
    	{			
			String result = xPath.evaluate(xPathExpression, context);
			return result;
		} 
    	catch (XPathExpressionException e) 
		{			
			LOGGER.info(xPath + " could not be evaluated !!");
			e.printStackTrace();
		}
    	
		return null;    
    }
	
	private NodeList evaluateXPathNodeSet(String xPathExpression)
	{		
    	try 
    	{			
			return (NodeList) xPath.evaluate(xPathExpression, xmlDocument, XPathConstants.NODESET);			
		} 
    	catch (XPathExpressionException e) 
		{
			LOGGER.info(xPath + " could not be evaluated !!");
			e.printStackTrace();
		}    	
		return null;  
	}
	
	public Boolean validateJson(String jsonSchema, String generatedJson) {
		// TODO Auto-generated method stub
		return null;
	}

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document xmlDocument;
	private XPath xPath;
	
}
