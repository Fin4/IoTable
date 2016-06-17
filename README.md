# IoTableParser
Parser helps automation contol engіneers create variables and tags for PLC and SCADA.
It uploads Excel file in specified format (ooxml, 2007 and later versions) validate it and check for duplicates.
After validation you may map I/O units (analog inputs, analog outputs, discrete inputs, discrete outputs)
to string you want.
Excel file must contain sheets:DI, AI, DO, AO
 Each sheet must have first row (header) columns with values : number, address, description, symbol, engUnits(only for AI sheet);
 Parsing of xls document continous to the first empty cell at each sheet.
