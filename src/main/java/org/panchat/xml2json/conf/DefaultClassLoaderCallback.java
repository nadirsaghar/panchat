/**
 * 
 */
package org.panchat.xml2json.conf;

import org.panchat.xml2json.conf.Configuration.IClassLoaderCallback;
import org.panchat.xml2json.macros.IMacro;

/**
 * @author nakull
 *
 */
public class DefaultClassLoaderCallback implements IClassLoaderCallback 
{
	/* (non-Javadoc)
	 * @see org.panchat.xml2json.conf.Configuration.IClassLoaderCallback#getClassByName(java.lang.String)
	 */
	public Class<? extends IMacro> getClassByName(String className) throws ClassNotFoundException 
	{
		return (Class<? extends IMacro>) Class.forName(className);
	}

}
