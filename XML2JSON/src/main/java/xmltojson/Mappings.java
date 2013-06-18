package xmltojson;

import ixmltojson.*;

public class Mappings implements IMappings
{	

	
	public String getMappingsAsString() {
		// TODO Auto-generated method stub
		return jsonSchema;
	}
	
	public Mappings(String mappings)
	{
		jsonSchema = mappings;
	}
	
	private String jsonSchema;
	
}