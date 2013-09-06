/**
 * 
 */
package org.panchat.xml2json.macros;

import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
/**
 * @author nakull
 *
 */
public class DefaultConvertor implements IMacro {

	/* (non-Javadoc)
	 * @see org.panchat.xml2json.macros.IMacro#getName()
	 */
	public String getName() {
		return "defaultConvertor";
	}

	/* (non-Javadoc)
	 * @see org.panchat.xml2json.macros.IMacro#getDescription()
	 */
	public String getDescription() {
		return "Convert an XML Node to JSON without any mappings information";
	}

	/* (non-Javadoc)
	 * @see org.panchat.xml2json.macros.IMacro#getAuthor()
	 */
	public String getAuthor() {
		return "nakull";
	}

	/* (non-Javadoc)
	 * @see org.panchat.xml2json.macros.IMacro#execute(com.google.gson.JsonArray, org.w3c.dom.Document, javax.xml.xpath.XPath)
	 */
	public JsonElement execute(JsonArray macroArguments, Document doc,
			XPath xPathEvaluator) throws MacroExeception {
		
		JsonElement value = macroArguments.get(0);
		String xpath = value.getAsString();
		NodeList nl = null;
		if(value.isJsonPrimitive())
		{
			XPathExpression expr = null;
			try 
			{
				expr = xPathEvaluator.compile( xpath  );
				nl = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);
			} 
			catch (Exception e) {				
				e.printStackTrace();
			}	
		}		
		
		StringWriter sw = new StringWriter();
		  try {
		    Transformer t = TransformerFactory.newInstance().newTransformer();
		    t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");		    
		    t.transform(new DOMSource(nl.item(0)), new StreamResult(sw));
		  } catch (TransformerException te) {
		    System.out.println("nodeToString Transformer Exception");
		  }
		String XMLNode = sw.toString();
		
		JSONObject xmlJSONObj = null;
		try {
			xmlJSONObj = XML.toJSONObject(XMLNode);
		} catch (JSONException e) {
			e.printStackTrace();
		}
        String jsonString = xmlJSONObj.toString();
        
        JsonParser parser = new JsonParser();
		return (JsonElement)parser.parse(jsonString);
        
        //return new com.google.gson.JsonObject(jsonString);       
		
		//return null;
	}

}
