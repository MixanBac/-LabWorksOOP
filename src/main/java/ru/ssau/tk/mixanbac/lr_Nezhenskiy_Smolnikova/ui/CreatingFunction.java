package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;



import static java.lang.module.ModuleFinder.compose;

public class CreatingFunction extends JFrame {

    List<Double> xValues = new ArrayList<>();
    List<Double> yValues = new ArrayList<>();
    private JLabel label = new JLabel("Введите колличество точек:");
    private JTextField countField = new JTextField();
    private JButton inputButton = new JButton("Ввод");
    private JButton createButton = new JButton("Создать");
    TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    TabulatedFunction function;
    public void TabulatedFunctionWindow() {
        this.setBounds(500, 500, 500, 500);
    }

}
