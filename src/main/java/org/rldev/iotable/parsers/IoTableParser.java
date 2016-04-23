package org.rldev.iotable.parsers;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.exceptions.NoSuchSheetException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IoTableParser {

    String parse(InputStream inputStream) throws IOException;

    String parse(File file) throws IOException, InvalidFormatException;

}
