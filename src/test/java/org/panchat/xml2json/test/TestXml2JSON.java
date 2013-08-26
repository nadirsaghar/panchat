package org.panchat.xml2json.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.panchat.xml2json.api.IMappings;
import org.panchat.xml2json.core.*;
import org.panchat.xml2json.macros.*;
import org.xml.sax.SAXException;

public class TestXml2JSON {

	@Test
	public void testConvertXmlToJsonExtensive() throws ParserConfigurationException, SAXException, IOException 
	{
		String schema;
		schema = new Scanner( new File("src/test/resources/Schema.txt") ).useDelimiter("\\A").next();
		IMappings mappings = new Mappings(schema);
		Xml2JSON xmlToJson = new Xml2JSON("src/test/resources/Test.xml");
		
		String result = xmlToJson.convertXmlToJson(mappings, null);
		System.out.println(result);
		//assert();
				
	}
	
	@Test
	public void testConvertXmlToJsonBasic() throws ParserConfigurationException, SAXException, IOException 
	{
		String schema;
		schema = new Scanner( new File("src/test/resources/SchemaBasic.txt") ).useDelimiter("\\A").next();
		IMappings mappings = new Mappings(schema);
		Xml2JSON xmlToJson = new Xml2JSON("src/test/resources/Test.xml");
		
		String result = xmlToJson.convertXmlToJson(mappings, null);
		System.out.println(result);
		//assert();
				
	}

	@Test
	public void testValidateJson() 
	{
		fail("Not yet implemented");
	}

}
