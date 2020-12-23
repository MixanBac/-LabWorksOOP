package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame {
    private JFrame frame = new JFrame();
    private JButton inputButtonTable = new JButton("Создать табулированную функцию");
    private JButton inputButtonFactory = new JButton("Выбрать тип фабрики");
    private JButton inputButtonMath = new JButton("Создать математическую функцию");
    private JButton inputButtonCalc = new JButton("Калькулятор");
    private JButton openButton = new JButton("Открыть функцию");
    private JButton saveButton = new JButton("Сохранить функцию");
    private List<Double> xValues = new ArrayList<>();
    private List<Double> yValues = new ArrayList<>();
    private MyTableModel1 tableModel = new MyTableModel1();
    private JTable table = new JTable(tableModel);
    private TabulatedFunctionFactory factory;

    public Menu() {
        setTitle("Меню");
        setBounds(200, 150, 1200, 600);
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
        inputButtonTable.addActionListener(event -> {
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
        inputButtonCalc.addActionListener(event ->
        {
            try {
                CalculationWindow.main(frame);
            } catch (Exception e) {
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
                FileWriter.main(tableModel.getFunction());
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
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButtonTable)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory)
                        .addComponent(openButton)
                        .addComponent(saveButton))
                .addComponent(tableScrollPane)
        );
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
            im = ImageIO.read(new File("C:\\Users\\1\\IdeaProjects\\LR_Nezhenskiy_Smolnikova\\Image\\Розовый.png"));
        } catch (IOException ignored) {
        }
        g.drawImage(im, 0, 0, null);
    }
}



