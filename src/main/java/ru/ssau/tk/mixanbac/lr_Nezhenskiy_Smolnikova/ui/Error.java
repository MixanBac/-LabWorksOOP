package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;

public class Error {
    private String generateMessageForException(Exception e) {
        if (e instanceof NumberFormatException) {
            return "Wrong number format";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Array isn't sorted";
        }
        return "Unknown error";
    }
}
