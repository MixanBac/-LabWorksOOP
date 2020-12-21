package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InconsistentFunctionsException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Error {
    Error(Component parent, Exception e) {
        showError(parent, e);
    }

    public void showError(Component parent, Exception e) {
        String head = generateMessageForException(e);
        JOptionPane.showMessageDialog(parent, "Ошибка!", head, JOptionPane.ERROR_MESSAGE);
    }

    private String generateMessageForException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return "Методу был передан недопустимый или несоответствующий аргумент.";
        }
        if (e instanceof IOException) {
            return "Исключение ввода-вывода.";
        }
        if (e instanceof NumberFormatException) {
            return "Приложение попыталось преобразовать строку в один из числовых типов, но строка не имеет подходящего формата.";
        }
        if (e instanceof InconsistentFunctionsException) {
            return "Разная длина массивов";
        }
        if (e instanceof ArrayIsNotSortedException) {
            return "Массив не сортирован.";
        }
        e.printStackTrace();
        return "Неизвестная ошибка.";
    }
}