/**
 * 
 */
package org.panchat.xml2json.bulk.test;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

import org.panchat.xml2json.bulk.helpers.DefaultFileWriter;
import org.panchat.xml2json.bulk.helpers.IWriter;
import org.panchat.xml2json.core.Mappings;
import org.panchat.xml2json.core.Xml2JSON;

/**
 * @author nakull
 *
 */

public class ClinicalUseCaseBulkTest {
	
	public static void main(String[] args)
	{	
		try 
		{
			String sourceDirectory = args[0];
			String destDirectory = args[1];
			
			File[] files = new File(sourceDirectory).listFiles(
					new FileFilter() {
						
						public boolean accept(File arg0) {
					
							return arg0.getName().contains("_clinical") && arg0.getName().endsWith("xml") ;
						}
					}
					);			
			String schema = new Scanner( new File("src/test/resources/clinical-usecase/clinical-schema.json") ).useDelimiter("\\A").next();
			Xml2JSON xml2json = new Xml2JSON();
			Mappings mappings = new Mappings(schema);
			IWriter writer = new DefaultFileWriter(destDirectory , "clinical-data");
			for (File file : files) 
			{
		        if (file.isFile()) 
		        {		        	
		        	String result = xml2json.convertXmlToJson(file.getPath(), mappings, null);
		        	writer.write(result);
		        }
			}   
			
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
