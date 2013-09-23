Panchat
=======
Panchat is a  java library that simplifies conversion of an arbitrary XML to an arbitrary JSON

The main aim of the project is to design and implement a library to allow conversion of an arbitrary XML structure to arbitrary JSON structure with the help of User defined mapping. 
Most open sourced xml2json libraries do the transformation in some standard way leaving little room for customization. 
The goal of this library is to create an elegant way of generating JSON out of any arbitrary XML format by using JSON Schema to encapsulate transformation rules and XPath notations to parse XML.

JSON schema describes the schema for JSON objects. XPath notations are used to extract nodes and attributes from XML. 
We extended the JSON schema to include certain attributes that essentially map to  XPath from the input XML. 
The algorithm is simple :
    read the mapping file(extended schema) and one-by-one populate JSON attributes by parsing XML. 

The combination of XPath mapped to JSON attributes provides a very convenient yet powerful way of describing transformation logic.


User Guide - http://imaging.cci.emory.edu/wiki/display/PANCHAT/User+Guide+-+Panchat
