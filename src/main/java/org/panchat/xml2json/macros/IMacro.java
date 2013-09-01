package org.panchat.xml2json.macros;

import javax.xml.xpath.XPath;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public interface IMacro {

	public String getName();
	public String getDescription();
	public String getAuthor();
	public JsonElement execute ( JsonArray  macroArguments , Document doc , XPath xPathEvaluator) throws MacroExeception;
}
