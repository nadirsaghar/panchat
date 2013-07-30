package org.panchat.xml2json.conf;

import java.util.HashMap;
import java.util.Map;

import org.panchat.xml2json.exception.MacroRegistrationException;
import org.panchat.xml2json.macros.ConcatMacro;
import org.panchat.xml2json.macros.IMacro;
import org.panchat.xml2json.macros.ToStringMacro;

public class Configuration {

	private Map<String,IMacro> macroRegistry;
	
	
	public Configuration()
	{
		macroRegistry = new HashMap<String, IMacro>();
		try 
		{
			registerMacro(ConcatMacro.class);
			registerMacro(ToStringMacro.class);
		} 
		catch (MacroRegistrationException e) 
		{			
			e.printStackTrace();
		}		
	}
	
	
	public IMacro lookupMacroByName(String macroname)
	{
		return macroRegistry.get(macroname);
	}
	public void registerMacro(Class<? extends IMacro> macroClass) throws MacroRegistrationException
	{
		try {
			IMacro macro = macroClass.newInstance();
			macroRegistry.put(macro.getName(), macro);
		} catch (Exception e) {
			throw new MacroRegistrationException(e);
		}
		
	}
	
	public void registerMacro(IMacro macro) throws MacroRegistrationException
	{
		try {
			 macroRegistry.put(macro.getName(), macro);
		} catch (Exception e) {
			throw new MacroRegistrationException(e);
		}
	}
	
	public void registerMacro(String classname , IClassLoaderCallback callback ) throws MacroRegistrationException
	{
		try {
			Class<? extends IMacro> macroClass = callback.getClassByName(classname);
			if(macroClass!=null)
			{
				IMacro macro = macroClass.newInstance();
				macroRegistry.put(macro.getName(), macro);
			}
			else
				throw new ClassNotFoundException(classname);
			
		} catch (Exception e) {
			throw new MacroRegistrationException(e);
		}
	}
	
	public static interface IClassLoaderCallback 
	{
		public Class<? extends IMacro> getClassByName(String classname) throws ClassNotFoundException;
	}
	
	
}
