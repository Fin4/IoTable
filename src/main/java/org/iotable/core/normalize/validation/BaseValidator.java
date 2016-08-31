package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.IoUnit;

public abstract class BaseValidator {

    private static final char cyrillicChars[] = {'\u0410', '\u0412', '\u0415', '\u041a', '\u041c', '\u041d', '\u041e',
            '\u0420', '\u0421', '\u0422', '\u0425', '\u0406', '\u0423'};
    private static final char latinChars[] = {'A', 'B', 'E', 'K', 'M', 'H', 'O', 'P', 'C', 'T', 'X', 'I', 'Y'};

    public IoUnit validate(final IoUnit ioUnit) {

        String address = ioUnit.address.trim();
        String desc = ioUnit.description
                    .replace(String.valueOf((char) 160), " ")
                    .replaceAll("«|»", "\"")
                    .trim();

        String symbol;
        if ((ioUnit.symbol == null) || ioUnit.symbol.isEmpty()) symbol = "res";
        else symbol = replaceCyrillic(ioUnit.symbol.trim());

        return new IoUnit(symbol, desc, address, ioUnit.number);
    }

    private String replaceCyrillic(String str) {

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < cyrillicChars.length; j++) {
                if (chars[i] == cyrillicChars[j]) chars[i] = latinChars[j];
            }
        }
        return new String(chars);
    }
}
