package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions;

import java.io.Serializable;

public class InconsistentFunctionsException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3029238084077949398L;

    public InconsistentFunctionsException() {
    }

    public InconsistentFunctionsException(String message) {
        super(message);
    }

}
