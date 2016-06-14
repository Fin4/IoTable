package org.rldev.iotable.codegenerators;

import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;

import java.io.IOException;
import java.util.Collection;

public interface CodeGenerator {

    String generateCode(Collection<? extends IoUnit> analogInputs, String format) throws WrongFormatException, IOException;
}
