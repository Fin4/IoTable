package org.rldev.iotable.parsers;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public interface IoTableDocument {

    String getAsJsonString() throws IOException;

}
