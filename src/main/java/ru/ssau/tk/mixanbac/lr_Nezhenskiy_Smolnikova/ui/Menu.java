package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.module.ModuleFinder.compose;

public class Menu extends JFrame {

    private JButton inputButton = new JButton("Создать табулированную функцию");
    private JButton inputButtonFactory = new JButton("Выбрать тип фабрики");
    private JButton inputButtonMath = new JButton("Создать математическую функцию");
    private JButton inputButtonCalc = new JButton("Калькулятор");
    private JButton openButton = new JButton("Открыть функцию");
    private JButton saveButton = new JButton("Сохранить функцию");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private MyTableModel1 tableModel = new MyTableModel1(xValues, yValues);
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;
    TabulatedFunction myFunction;

    public Menu() {
        setTitle("Menu");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public void wrapper(int countOld, int countNew) {
        tableModel.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModel.getFunction().getX(i));
            yValues.add(tableModel.getFunction().getY(i));
        }
    }

    public void actionPerformed() {
        inputButton.addActionListener(event -> {
                    try {
                        int countOld = xValues.size();
                        MyFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                        int countNew = tableModel.getFunction().getCount();
                        wrapper(countOld, countNew);
                    } catch (Exception e) {
                        if (e instanceof NullPointerException) {
                            e.printStackTrace();
                        } else
                            new Error(this, e);
                    }
                }
        );
        inputButtonMath.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                MathFunctionWindow.main(factory, data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapper(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new Error(this, e);
            }
        });
        inputButtonFactory.addActionListener(event -> {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new Error(this, e);
            }
        });
        openButton.addActionListener(event -> {
            try {
                int countOld = xValues.size();
                FileReader.main(data -> tableModel.setFunction(data));
                int countNew = tableModel.getFunction().getCount();
                wrapper(countOld, countNew);
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new Error(this, e);
            }
        });
        saveButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                for (int i = 0; i < xValues.size(); i++) {
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                tableModel.setFunction(factory.create(x, y));
                Writer.main(tableModel.getFunction());
            } catch (Exception e) {
                if (e instanceof NullPointerException) {
                    e.printStackTrace();
                } else
                    new Error(this, e);
            }
        });
    }


    void compose() {
        setContentPane(new BgPanel());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButton)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory))
                .addComponent(openButton)
                .addComponent(saveButton))
        ;
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButton)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory))
                .addComponent(openButton)
                .addComponent(saveButton))
        ;
    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);
    }
}

class BgPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("C:\\Users\\1\\Desktop\\games-4952694_960_720.jpg"));
        } catch (IOException ignored) {
        }
        g.drawImage(im, 0, 0, null);
    }
}




