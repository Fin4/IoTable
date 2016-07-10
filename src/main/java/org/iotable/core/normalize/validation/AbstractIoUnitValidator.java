package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class AbstractIoUnitValidator {

    private static final char cyrillicChars[] = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'Х', 'І', 'У'};
    private static final char latinChars[] = {'A', 'B', 'E', 'K', 'M', 'H', 'O', 'P', 'C', 'T', 'X', 'I', 'Y'};

    List<? extends IoUnit> baseValidate(List<? extends IoUnit> ioUnits) {

        for (IoUnit ioUnit : ioUnits) {

            ioUnit.setAddress(ioUnit.getAddress().trim());

            String desc = ioUnit.getDescription().replace(String.valueOf((char) 160), " ").trim();

            ioUnit.setDescription(desc.replaceAll("«|»", "\""));

            if ((ioUnit.getSymbol() == null) || ioUnit.getSymbol().isEmpty()) {
                ioUnit.setSymbol(ioUnit.getClass().getSimpleName() + ".res" + ioUnit.getNumber());
            } else ioUnit.setSymbol(replaceCyrillic(ioUnit.getSymbol().trim()));
        }

        return ioUnits;
    }

    String replaceCyrillic(String str) {

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < cyrillicChars.length; j++) {
                if (chars[i] == cyrillicChars[j]) chars[i] = latinChars[j];
            }
        }
        return new String(chars);
    }
}
