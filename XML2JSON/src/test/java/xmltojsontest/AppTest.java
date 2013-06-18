package xmltojsontest;

import ixmltojson.*;

import xmltojson.*;

import java.io.*;
import java.util.*;

public class AppTest
{
	public static void main(String[] args)
	{
		//FileReader fileReader = new FileReader("Schema.txt");
		//BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		try 
		{
			String schema;
			schema = new Scanner( new File("/home/nakull/GSoC/panchat/XML2JSON/src/test/java/xmltojsontest/Schema.txt") ).useDelimiter("\\A").next();
			IMappings mappings = new Mappings(schema);
			XmlToJson xmlToJson = new XmlToJson("/home/nakull/GSoC/panchat/XML2JSON/src/test/java/xmltojsontest/Test.xml");
			String xml = new Scanner( new File("/home/nakull/GSoC/panchat/XML2JSON/src/test/java/xmltojsontest/Test.xml") ).useDelimiter("\\A").next();
			
			String result = xmlToJson.convertXmlToJson(xml, mappings, null);
			System.out.println(result);
			PrintWriter out = new PrintWriter("/home/nakull/GSoC/panchat/XML2JSON/src/test/java/xmltojsontest/Success.txt");
			out.println(result);
			out.close();
		} 
		
		catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
		
	}
}