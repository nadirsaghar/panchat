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
	public String Execute(String[] args, InputSource context) 
	{
		String result = "";
		
		XPath xpath = xPathFactory.newXPath();
    	try 
    	{   for(String arg : args)
    		{
	    		XPathExpression expr = xpath.compile(arg);
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