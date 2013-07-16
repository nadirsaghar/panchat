/**
 * 
 */
package org.panchat.xml2json.macros;

/**
 * @author nakull
 *
 */
public abstract class AbstractMacro 
{
	private String Name;
	
	private String Description;
	
	public abstract void InitializeMetaData(String Name, String Description);
	
	public abstract void Execute(String[] args);
	
}

