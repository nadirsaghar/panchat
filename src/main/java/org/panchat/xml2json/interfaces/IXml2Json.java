/**
 * 
 */
package org.panchat.xml2json.interfaces;

/**
 * @author nakull
 *
 */
public interface IXml2Json {
	
	public String convertXmlToJson(String xml,IMappings mappings, ISettings settings);
	
	public Boolean validateJson(String jsonSchema, String generatedJson);

}
