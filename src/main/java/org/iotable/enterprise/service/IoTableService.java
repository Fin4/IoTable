package org.iotable.enterprise.service;


import org.iotable.core.document.IoTableDocument;
import org.iotable.core.model.IoTable;

public interface IoTableService {

    IoTable getFromWorkbook(final IoTableDocument document);

    IoTable validate(final IoTable ioTable);
}
