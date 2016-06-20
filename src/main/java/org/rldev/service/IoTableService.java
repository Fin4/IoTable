package org.rldev.service;


import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.iotable.document.IoTableDocument;
import org.rldev.iotable.model.IoTable;

import java.io.IOException;

public interface IoTableService {

    IoTable getFromWorkbook(final IoTableDocument document);

    IoTable validate(final IoTable ioTable);
}
