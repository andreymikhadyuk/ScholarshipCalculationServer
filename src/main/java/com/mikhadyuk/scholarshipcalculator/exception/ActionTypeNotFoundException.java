package com.mikhadyuk.scholarshipcalculator.exception;

public class ActionTypeNotFoundException extends Exception {
    public ActionTypeNotFoundException() {
    }

    public ActionTypeNotFoundException(String message) {
        super(message);
    }

    public ActionTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ActionTypeNotFoundException(Throwable cause) {
        super(cause);
    }

    public ActionTypeNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
