package org.rldev.iotable.codegenerators;

import org.rldev.iotable.model.IoUnit;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;

import java.io.IOException;
import java.util.Collection;

public interface CodeGenerator {

    String generateCode(String json, String format) throws WrongFormatException, IOException;

    String generateCode(Collection<? extends IoUnit> analogInputs, String format) throws WrongFormatException, IOException;
}
