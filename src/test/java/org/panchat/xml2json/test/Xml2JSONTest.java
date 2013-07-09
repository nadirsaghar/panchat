/**
 * 
 */
package org.panchat.xml2json.test;

import java.io.*;
import java.util.*;

import org.panchat.xml2json.api.*;
import org.panchat.xml2json.core.*;

/**
 * @author nakull
 *
 */
public class Xml2JSONTest {
	
	public static void main(String[] args)
	{
		//FileReader fileReader = new FileReader("Schema.txt");
		//BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		try 
		{
			String schema;
			schema = new Scanner( new File("/home/nakull/GSoC/panchat/src/test/resources/Schema.txt") ).useDelimiter("\\A").next();
			IMappings mappings = new Mappings(schema);
			Xml2JSON xmlToJson = new Xml2JSON("/home/nakull/GSoC/panchat/src/test/resources/Test.xml");
			String xml = new Scanner( new File("/home/nakull/GSoC/panchat/src/test/resources/Test.xml") ).useDelimiter("\\A").next();
			
			String result = xmlToJson.convertXmlToJson(xml, mappings, null);
			System.out.println(result);
			//PrintWriter out = new PrintWriter("home/nakull/GSoC/panchat/src/test/resources/Success2.txt");
			//out.println(result);
			//out.close();
		} 
		
		catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		
	}


}