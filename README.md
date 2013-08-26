Panchat
=======

A java library that simplifies conversion of an arbitrary XML to an arbitrary JSON 

The main aim of the project is to design and implement a library to allow conversion of XML data sets to JSON in a custom defined manner - with the help of user defined mappings. Round trip conversion - XML -> JSON -> XML will also be supported. The use case is that currently all the open source tools which perform this conversion do not allow mapping specific attributes and nodes. They transform the complete XML into a JSON without taking into consideration user specifications. In most cases only a small portion of the XML is required to be transformed( to specific JSON fields) - which can be easily specified by XPath and JSON Schema - thus the requirement of this utility. The library designed will be easy to use and have an extensible and simple Interface. The mappings file will be a JSON Schema file with the description tag for each element containing the XPath expression for the same. Only those elements which are required to be converted from XML will be specified by the user in the JSON Schema. Some limitations might arise as with just XPath and JSON Schema there might be ambiguities and anomalies in the conversion. I will be documenting all these and trying to rectify and reduce the limitations of this library.

User Guide - http://imaging.cci.emory.edu/wiki/display/PANCHAT/User+Guide+-+Panchat
