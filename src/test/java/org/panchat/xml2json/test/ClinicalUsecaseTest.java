/**
 * 
 */
package org.panchat.xml2json.test;

import java.io.File;
import java.util.Scanner;

import org.panchat.xml2json.core.Mappings;
import org.panchat.xml2json.core.Xml2JSON;

/**
 * @author nadir
 *
 */
public class ClinicalUsecaseTest {
	
	public static void main(String[] args)
	{	
		try 
		{
			String schema = new Scanner( new File("src/test/resources/clinical-usecase/clinical-schema.json") ).useDelimiter("\\A").next();
			Xml2JSON xml2json = new Xml2JSON();
			Mappings mappings = new Mappings(schema);
			String result = xml2json.convertXmlToJson("src/test/resources/clinical-usecase/clinical-data.xml", mappings, null);
			System.out.println(result);
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
