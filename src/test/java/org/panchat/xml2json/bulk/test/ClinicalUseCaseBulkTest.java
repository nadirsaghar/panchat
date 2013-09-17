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
			
			/*File[] files = new File(sourceDirectory).listFiles(
					new FileFilter() {
						
						public boolean accept(File arg0) {
					
							return arg0.getName().contains("_clinical") && arg0.getName().endsWith("xml") ;
						}
					}
					);*/
			File[] files = new File(sourceDirectory).listFiles();
			String schema = new Scanner( new File("src/test/resources/clinical-usecase/clinical-schema.json") ).useDelimiter("\\A").next();
			Xml2JSON xml2json = new Xml2JSON();
			Mappings mappings = new Mappings(schema);
			IWriter writer = new DefaultFileWriter(destDirectory , "clinical-data");
			int fileCount = 0;long conversionTime = 0, writingTime = 0, sizeOfFile = 0;
			for (File file : files) 
			{
		        if (file.isFile()) 
		        {
		        	fileCount++;
		        	sizeOfFile += file.length();
		        	long t1 = System.nanoTime();
		        	String result = xml2json.convertXmlToJson(file.getPath(), mappings, null);
		        	long t2 = System.nanoTime();
		        	conversionTime += (t2 - t1);
		        	writer.write(result);
		        	long t3 = System.nanoTime();
		        	writingTime += (t3 - t2);
		        	//System.out.println(file.getName() + "/t" + );
		        }
			}
			System.out.println("Total Files : " + fileCount + "/t" +
					"Average File Size : " + sizeOfFile/fileCount +
					"Average Conversion Time : " + conversionTime/fileCount + 
					"Average Writing Time : " + writingTime/fileCount);			
			
		} 
		
		catch (Exception e) 
		{			
			e.printStackTrace();
		}
		
	}


}
