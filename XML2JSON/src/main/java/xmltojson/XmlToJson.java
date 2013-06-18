package xmltojson;


import java.io.IOException;
import java.util.Iterator;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ixmltojson.*;
import org.json.*;
import org.json.zip.*;


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

	
	
	public String convertXmlToJson(String xml, IMappings mappings,
			ISettings settings) 
	{
		// TODO Auto-generated method stub
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
	     }	 
		 
		 return null;
	}
	
	

	
	public Boolean validateJson(String jsonSchema, String generatedJson) 
	{
		// TODO Auto-generated method stub
		return null;
	}
    
	
	
	
}
