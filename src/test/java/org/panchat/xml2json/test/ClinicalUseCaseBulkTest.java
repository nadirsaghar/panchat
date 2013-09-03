/**
 * 
 */
package org.panchat.xml2json.test;

import java.io.*;
import java.util.*;

import org.panchat.xml2json.core.*;
import org.panchat.xml2json.helpers.*;

/**
 * @author nakull
 *
 */

public class ClinicalUseCaseBulkTest {
	
	public static void main(String[] args)
	{	
		try 
		{
			
			File[] files = new File(args[0]).listFiles();			
			String schema = new Scanner( new File("src/test/resources/clinical-usecase/clinical-schema.json") ).useDelimiter("\\A").next();
			Xml2JSON xml2json = new Xml2JSON();
			Mappings mappings = new Mappings(schema);
			IWriter writer = new DefaultFileWriter();
			for (File file : files) 
			{
		        if (file.isFile()) 
		        {		        	
		        	String result = xml2json.convertXmlToJson(file.getPath(), mappings, null);
		        	
		        	String path = args[1];
		        	path += "/";
		        	path += file.getName();
		        	path = path.substring(0, path.length() - 4);
		        	
		        	Object[] arguments = new Object[1];
		        	arguments[0] = path;
		        	writer.write(result, arguments);
		        	
		        }
			}   
			
			String result = xml2json.convertXmlToJson("src/test/resources/clinical-usecase/clinical-data.xml", mappings, null);
			System.out.println(result);
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
