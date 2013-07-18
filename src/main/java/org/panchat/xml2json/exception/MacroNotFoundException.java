package org.panchat.xml2json.exception;

public class MacroNotFoundException extends Exception {

	private String macroname ;
	public MacroNotFoundException(String name) {
		macroname = name;
	}

	public String getMessage()
	{
		return "Macro [" + macroname + "] not found";
	}
}
