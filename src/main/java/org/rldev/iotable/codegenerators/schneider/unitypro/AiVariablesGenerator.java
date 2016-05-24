package org.rldev.iotable.codegenerators.schneider.unitypro;


import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.io.IOException;
import java.util.Collection;

public class AiVariablesGenerator implements CodeGenerator {

    @Override
    public String generateCode(String json, String format) throws WrongFormatException, IOException {
        return "";
    }

    @Override
    public String generateCode(Collection<? extends IoUnit> analogInputs, String format) throws WrongFormatException, IOException {

        return null;
    }
}
