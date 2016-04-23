package org.rldev.iotable.exceptions;

public class WrongSheetFormatException extends Exception {

    public WrongSheetFormatException() {
    }

    public WrongSheetFormatException(String message) {
        super(message);
    }
}
