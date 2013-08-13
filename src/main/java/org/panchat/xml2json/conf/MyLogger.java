package org.panchat.xml2json.conf;

import java.util.logging.Logger;

public class MyLogger {
	
	private static Logger myLogger = Logger.getLogger("myLogger");
	
	public static Logger getLogger()
	{
		return myLogger;
	}
	private MyLogger()
	{
		
	}

}
