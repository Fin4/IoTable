# IoTableParser
Parser helps automation contol engіneers create variables and tags for PLC and SCADA.
It uploads Excel file in specified format (ooxml, 2007 and later versions) validate it and check for duplicates.
After validation you may map I/O units (analog inputs, analog outputs, discrete inputs, discrete outputs)
to string you want.
Excel file must contain sheets:
DI, AI, DO, AO - digital inputs sheet in format
 - first row (header) of each sheet must have columns with values : number, address, description, symbol, engUnits(only for AI sheet);
 parsing of xls document continous to the first empty cell at each sheet.
