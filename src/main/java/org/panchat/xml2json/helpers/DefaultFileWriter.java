/**
 * 
 */
package org.panchat.xml2json.helpers;

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
	public void write(String JSON, Object[] arguments) {
		// TODO Auto-generated method stub
		
		String path = (String)arguments[0];
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
