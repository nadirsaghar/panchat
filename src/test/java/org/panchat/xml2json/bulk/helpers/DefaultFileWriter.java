/**
 * 
 */
package org.panchat.xml2json.bulk.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author nakull
 *
 */
public class DefaultFileWriter implements IWriter {
	
	/* (non-Javadoc)
	 * @see org.panchat.xml2json.test.IWriter#write(java.lang.String)
	 */
	
	private File outputDirectory;
	private Integer count;
	private String filenamePrefix;
	public DefaultFileWriter(String outputDirectory , String filenamePrefix)
	{
		this.outputDirectory = new File(outputDirectory);
		this.filenamePrefix = filenamePrefix;
		this.count = 0;
	}
	
	public void write(String JSON) {

		File path = new File(outputDirectory , String.format("%s-%s.json", filenamePrefix , this.count++ ));
		PrintWriter out;
		try 
		{
			out = new PrintWriter(path);
			out.println(JSON);	    	
	    	out.close();
		} catch (FileNotFoundException e) 
		{			
			e.printStackTrace();
		}
    	
	}

}
