package ixmltojson;

/// <summary>
/// Interface for XML to JSON conversion
/// </summary>
public interface IXmlToJson
{
	/// <summary>
	/// 
	/// </summary>
	/// <param name="xml">Xml - read as as a String </param>
	/// <param name="mappings">Reference for a IMappings variable - containing the mapping info - which wraps the JSON Schema</param>
	/// <param name="settings">Reference for a IMappings variable - containing the mapping info - which wraps the JSON Schema</param>
	/// <returns> Converted Json</returns>
	public String convertXmlToJson(String xml,IMappings mappings, ISettings settings);

	/// <summary>
	/// Validate a Json File against a Schema
	/// </summary>
	/// <param name="jsonSchema">The Json Schema - represented as a String</param>
	/// <param name="generatedJson">The Json to be validated</param>
	/// <returns>True/False - depending on pass/fail</returns>
	public Boolean validateJson(String jsonSchema, String generatedJson);


} 


