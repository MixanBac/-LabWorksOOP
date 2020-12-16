package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static java.lang.module.ModuleFinder.compose;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButton = new JButton("Create the table");
    private JButton inputButtonFactory = new JButton("Choose factory");
    private JButton inputButtonMath = new JButton("Choose Math function");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private AbstractTableModel tableModel = new TableModel(xValues, yValues);
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;
    private TabulatedFunction function;
    public Menu() {
        setTitle("Menu");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);

    }


    public void actionPerformed() {
        inputButton.addActionListener(event -> {
                    try {
                        MyFrame.main(factory, data -> {
                            this.function = data;
                        });
                        int count = function.getCount();
                        tableModel.fireTableDataChanged();
                        for (int i = 0; i < count; i++) {
                            xValues.add(function.getX(i));
                            yValues.add(function.getY(i));
                        }
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
        );

        inputButtonMath.addActionListener(event -> {
            try {
                MathFunctionWindow.main(factory);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
        inputButtonFactory.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
            JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButton))
                        .addComponent(inputButtonFactory)
                        .addComponent(inputButtonMath)
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButton))
                        .addComponent(inputButtonFactory)
                        .addComponent(inputButtonMath)
                        .addComponent(tableScrollPane)
        );
    }
}


