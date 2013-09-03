/**
 * 
 */
package org.panchat.xml2json.api;

/**
 * @author nakull
 *
 */
 
 /** Change interface to IXml2JSON **/
public interface IXml2JSON {
	
	public String convertXmlToJson(String XMLFilePath, IMappings mappings, ISettings settings);
	
	public Boolean validateJson(String jsonSchema, String generatedJson);

}
