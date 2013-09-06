/**
 * 
 */
package org.panchat.xml2json.test;

import java.io.File;
import java.util.Scanner;

import org.panchat.xml2json.api.IMappings;
import org.panchat.xml2json.core.Mappings;
import org.panchat.xml2json.core.Xml2JSON;

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
			schema = new Scanner( new File("src/test/resources/Schema.txt") ).useDelimiter("\\A").next();
			IMappings mappings = new Mappings(schema);
			Xml2JSON xmlToJson = new Xml2JSON();			
			String result = xmlToJson.convertXmlToJson("src/test/resources/Test.xml", mappings, null);
			System.out.println(result);
			//PrintWriter out = new PrintWriter("home/nakull/GSoC/panchat/src/test/resources/Success2.txt");
			//out.println(result);
			//out.close();
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
