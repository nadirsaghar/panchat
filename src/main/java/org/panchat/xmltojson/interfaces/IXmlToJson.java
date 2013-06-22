/**
 * 
 */
package org.panchat.xmltojson.interfaces;

/**
 * @author nakull
 *
 */
public interface IXmlToJson {
	
	public String convertXmlToJson(String xml,IMappings mappings, ISettings settings);
	
	public Boolean validateJson(String jsonSchema, String generatedJson);

}
