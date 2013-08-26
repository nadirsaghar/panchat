/**
 * 
 */
package org.panchat.xml2json.test;

import java.io.*;
import java.io.ObjectInputStream.GetField;
import java.util.*;

import org.panchat.xml2json.api.*;
import org.panchat.xml2json.core.*;

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
			Xml2JSON xml2json = new Xml2JSON("src/test/resources/clinical-usecase/clinical-data.xml");
			Mappings mappings = new Mappings(schema);
			String result = xml2json.convertXmlToJson(null, mappings, null);
			System.out.println(result);
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
