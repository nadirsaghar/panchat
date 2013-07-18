package org.panchat.xml2json.macros;

import org.panchat.xml2json.exception.MacroExeception;
import org.w3c.dom.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

public class ConcatMacro implements IMacro {

	public String getName() {
		return "concat";
	}

	public String getDescription() {

		return "macro to concatenate string values";
	}

	public String getAuthor() {

		return "nadir";
	}


	public JsonElement execute(JsonArray macroArguments , Document context) throws MacroExeception {
		/** Implement concatenation here **/
		return null;
	}

}
