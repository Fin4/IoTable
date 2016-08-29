package org.iotable.core.document;


import org.iotable.core.model.IoTable;

public interface IoTableDocument {

    String getAsJsonString();

    IoTable getAsIoTable();

}
