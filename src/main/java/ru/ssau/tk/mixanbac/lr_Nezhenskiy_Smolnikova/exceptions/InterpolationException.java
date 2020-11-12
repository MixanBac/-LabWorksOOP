package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions;

import java.io.Serializable;

public class InterpolationException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1574200543699859463L;

    public InterpolationException() {
    }

    public InterpolationException(String message) {
        super(message);
    }
}
