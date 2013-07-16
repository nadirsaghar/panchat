/**
 * 
 */
package org.panchat.xml2json.macros;

import javax.xml.parsers.*;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import com.google.gson.*;
/**
 * @author nakull
 *
 */
public abstract class Concat extends AbstractMacro 
{

	/**
	 * @param Name
	 * @param Description
	 */
	public Concat() 
	{
		super("Concat", "Concats results of multiple XPath Expressions");
		xPathFactory = XPathFactory.newInstance();
	}

	/* (non-Javadoc)
	 * @see org.panchat.xml2json.macros.AbstractMacro#Execute(java.lang.String[])
	 */
	@Override
	public String Execute(JsonArray args, Object context) 
	{
		String result = "";
		
		XPath xpath = xPathFactory.newXPath();
    	try 
    	{   for(int i =0; i<args.size();i++)
    		{
    			JsonElement value = args.get(i);    			
	    		XPathExpression expr = xpath.compile(value.getAsString());
				result += expr.evaluate(context);
    		}
									
		} 
    	catch (XPathExpressionException e) 
		{			
			e.printStackTrace();
		}    	
		return result;  

	}
	
	private XPathFactory xPathFactory;
	
}
