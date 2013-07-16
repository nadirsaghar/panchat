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
		
	public AbstractMacro(String Name, String Description)
	{
		this.Name = Name;
		this.Description = Description;
	}
	
	public abstract void Execute(String[] args);
	
}

