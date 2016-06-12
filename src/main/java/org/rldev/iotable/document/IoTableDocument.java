package org.rldev.iotable.document;

import java.io.IOException;

public interface IoTableDocument {

    String getAsJsonString() throws IOException;

}
