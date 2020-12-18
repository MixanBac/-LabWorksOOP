package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalculationWindow extends JFrame {
    List<Double> xOne = new ArrayList<>();
    List<Double> yOne = new ArrayList<>();
    List<Double> xTwo = new ArrayList<>();
    List<Double> yTwo = new ArrayList<>();
    List<Double> xThree = new ArrayList<>();
    List<Double> yThree = new ArrayList<>();
    private Map<String, Integer> nameFunctionMap = new LinkedHashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();
    JButton calculate = new JButton("Calculate");
    TabulatedFunctionFactory factoryResult=new ArrayTabulatedFunctionFactory();
    TabulatedFunctionFactory factoryOne;
    TabulatedFunctionFactory factoryTwo;
    TabulatedFunction result;
    TabulatedFunction one;
    TabulatedFunction two;

    public void fillMap() {
        nameFunctionMap.put("Сумма(+)", 1);
        nameFunctionMap.put("Разность(-)", 2);
        nameFunctionMap.put("Умножение(*)", 3);
        nameFunctionMap.put("Деление(/)", 4);
        for (String string : nameFunctionMap.keySet()) {
            functionComboBox.addItem(string);
        }
    }

    public JPanel firstFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        AbstractTableModel tableModel = new MyTableModel(xOne, yOne) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return false;
                    case 1:
                        return false;
                    case 2:
                        return true;
                }
                return false;
            }

            public double getX(int row) {
                return (double) this.getValueAt(row, 1);
            }

            public double getY(int row) {
                return (double) this.getValueAt(row, 2);
            }

            public void setY(double aValue, int row) {
                this.setValueAt(aValue, row, 2);
            }
        };
        JLabel label = new JLabel("Первая");
        JTable table1 = new JTable(tableModel);
        JButton saveOrOpen = new JButton("Сохранить или открыть");
        //JButton save = new JButton("Save");
        //JButton open = new JButton("Open");
        JButton createByArray = new JButton("Создать таблицу");
        JButton createByFunc = new JButton("Создать функцию");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        //addListenerCreateByTable(createByArray, one);
        addListenerCreateByTable(createByArray, 1);
        panel.add(createByFunc);
        addListenerCreateByFunc(createByFunc, tableModel);
        panel.add(saveOrOpen);
        addListenerForSaveOrOpen(saveOrOpen);
        //panel.add(open);
        panel.setPreferredSize(new Dimension(50, 75));
        return panel;
    }

    public JPanel secondFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        AbstractTableModel tableModel = new MyTableModel(xTwo, yTwo) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:
                        return false;
                    case 1:
                        return false;
                    case 2:
                        return true;
                }
                return false;
            }

            public double getX(int row) {
                return (double) this.getValueAt(row, 1);
            }

            public double getY(int row) {
                return (double) this.getValueAt(row, 2);
            }

            public void setY(double aValue, int row) {
                this.setValueAt(aValue, row, 2);
            }
        };
        JLabel label = new JLabel("Второй");
        JTable table1 = new JTable(tableModel);
        //JButton save = new JButton("Save");
        //JButton open = new JButton("Open");
        JButton saveOrOpen = new JButton("Сохранить или открыть");
        JButton createByArray = new JButton("Создать таблицу");
        JButton createByFunc = new JButton("Создать функцию");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        //addListenerCreateByTable(createByArray, two);
        addListenerCreateByTable(createByArray);
        panel.add(createByFunc);
        addListenerCreateByFunc(createByFunc, tableModel, 1);
        panel.add(saveOrOpen);
        addListenerForSaveOrOpen(saveOrOpen, two);
        //panel.add(save);
        //panel.add(open);
        panel.setPreferredSize(new Dimension(50, 75));
        return panel;
    }

    public JPanel resultFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        AbstractTableModel tableModel = new MyTableModel(xThree, yThree) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            public double getX(int row) {
                return (double) this.getValueAt(row, 1);
            }

            public double getY(int row) {
                return (double) this.getValueAt(row, 2);
            }

            public void setY(double aValue, int row) {
                this.setValueAt(aValue, row, 2);
            }
        };
        JLabel label = new JLabel("Результат");
        JTable table1 = new JTable(tableModel);
        JButton save = new JButton("Сохранить");
        JButton createByArray = new JButton("Создать таблицу");
        JButton createByFunc = new JButton("Создать функцию");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        panel.add(createByFunc);
        panel.add(save);
        addListenerForSaveOrOpen(save, 1);
        panel.setPreferredSize(new Dimension(50, 75));
        return panel;
    }

    public CalculationWindow() {
        super("Калькулятор");
        this.setBounds(0, 100, 800, 600);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (factoryOne instanceof LinkedListTabulatedFunction) {
            one = new LinkedListTabulatedFunction();
        } else {
            one = new ArrayTabulatedFunction();
        }
        fillMap();
        compose();
        addButtonListeners();
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JPanel firstPanel = firstFunc();
        JPanel secondPanel = secondFunc();
        JPanel resultPanel = resultFunc();
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(firstPanel)
                        .addComponent(functionComboBox)
                        .addComponent(secondPanel)
                        .addComponent(calculate)
                        .addComponent(resultPanel))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(firstPanel)
                        .addComponent(functionComboBox)
                        .addComponent(secondPanel)
                        .addComponent(calculate)
                        .addComponent(resultPanel))
        );
    }

    public static void main(String[] args) {
        CalculationWindow app = new CalculationWindow();
        app.setVisible(true);
    }

    public static void main(JFrame args) {
        CalculationWindow app = new CalculationWindow();
        app.setVisible(true);
    }

    public void addButtonListeners() {
        addListenerForCalculate();
    }

    public void addListenerForCalculate() {
        calculate.addActionListener(event -> {
            try {
                TabulatedFunctionOperationService operate = new TabulatedFunctionOperationService(factoryResult);
                String func = (String) functionComboBox.getSelectedItem();
                int selectedFunction = nameFunctionMap.get(func);
                switch (selectedFunction) {
                    case 1:
                        result = operate.sum(one, two);
                        break;
                    case 2:
                        result = operate.subtract(one, two);
                        break;
                    case 3:
                        result = operate.multiply(one, two);
                        break;
                    case 4:
                        result = operate.divide(one, two);
                        break;
                }
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(JButton save, TabulatedFunction myFunction) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(f -> two = f);
                int k = 1;
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(JButton save, int k) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(f -> result = f);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(JButton save) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(f -> one = f);
                int k = 1;
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByTable(JButton button, TabulatedFunction myFunction) {
        button.addActionListener(event -> {
            try {
                MyFrame.main(myFunction);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByTable(JButton button, int k) {
        button.addActionListener(event -> {
            try {
                MyFrame.main(f -> first = f);
            } catch (Exception e) {
                new ErrorWindow(this, e);
            }
        });
    }

    public void addListenerCreateByTable(JButton button) {
        button.addActionListener(event -> {
            try {
                MyFrame.main(f -> two = f);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }


    public void addListenerCreateByFnc(JButton button, AbstractTableModel tableModel) {
        button.addActionListener(event -> {
            try {
                MathFunctionWindow.main(f -> one = f);
                tableModel.fireTableDataChanged();
                int z=1;
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }
    public void addListenerCreateByFnc(JButton button, AbstractTableModel tableModel, int k) {
        button.addActionListener(event -> {
            try {
                MathFunctionWindow.main(f -> two = f);
                tableModel.fireTableDataChanged();
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }
}



