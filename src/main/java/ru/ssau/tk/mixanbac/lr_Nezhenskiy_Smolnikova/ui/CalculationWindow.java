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

public class CalculationWindow extends JDialog {
    List<Double> xOne = new ArrayList<>();
    List<Double> yOne = new ArrayList<>();
    List<Double> xTwo = new ArrayList<>();
    List<Double> yTwo = new ArrayList<>();
    List<Double> xThree = new ArrayList<>();
    List<Double> yThree = new ArrayList<>();
    private Map<String, Integer> nameFunctionMap = new LinkedHashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();
    JButton calculate = new JButton("Калькулятор");
    TabulatedFunctionFactory factoryResult = new ArrayTabulatedFunctionFactory();
    TabulatedFunctionFactory factoryOne;
    TabulatedFunction result = new ArrayTabulatedFunction();
    TabulatedFunction one = new ArrayTabulatedFunction();
    TabulatedFunction two = new ArrayTabulatedFunction();
    AbstractTableModel tableModel;
    AbstractTableModel tableModel1;
    AbstractTableModel tableModel2;

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
        AbstractTableModel tableModel = new MyTableModel2(one) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:

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
        this.tableModel1 = tableModel;
        JLabel label = new JLabel("Первая");
        JTable table1 = new JTable(tableModel);
        JButton saveOrOpen = new JButton("Сохранить или открыть");
        JButton createByArray = new JButton("Создать таблицу");
        JButton createByFunc = new JButton("Создать функцию");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        addListenerCreateByTable(createByArray, tableModel);
        panel.add(createByFunc);
        addListenerCreateByFunc(createByFunc, tableModel);
        panel.add(saveOrOpen);
        addListenerForSaveOrOpen(saveOrOpen, tableModel);
        panel.setPreferredSize(new Dimension(50, 75));
        return panel;
    }

    public JPanel secondFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        AbstractTableModel tableModel = new MyTableModel2(two) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                switch (columnIndex) {
                    case 0:

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
        this.tableModel2 = tableModel;
        JLabel label = new JLabel("Второй");
        JTable table1 = new JTable(tableModel);

        JButton saveOrOpen = new JButton("Сохранить или открыть");
        JButton createByArray = new JButton("Создать таблицу");
        JButton createByFunc = new JButton("Создать функцию");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);

        addListenerCreateByTable(createByArray, tableModel1, 1);
        panel.add(createByFunc);
        addListenerCreateByFunc(createByFunc, tableModel, 1);
        panel.add(saveOrOpen);
        addListenerForSaveOrOpen(1, saveOrOpen, tableModel);

        panel.setPreferredSize(new Dimension(500, 750));
        return panel;
    }

    public JPanel resultFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.BLACK, 1));
        AbstractTableModel tableModel = new MyTableModel2(result) {
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

        };
        this.tableModel = tableModel;
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
        addListenerForSaveOrOpen(save, tableModel, 1);
        panel.setPreferredSize(new Dimension(50, 75));
        return panel;
    }

    public CalculationWindow() {
        setModal(true);
        setTitle("Калькулятор");
        this.setBounds(0, 100, 500, 600);
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
                String myFunction = (String) functionComboBox.getSelectedItem();
                int selectedFunction = nameFunctionMap.get(myFunction);
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
                refreshFirst(result, tableModel, 3);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(JButton save, AbstractTableModel tableModel, int k) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(result);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(int k, JButton save, AbstractTableModel tableModel) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(two, f -> {
                    two = f;
                    refreshFirst(two, tableModel, 1);
                });

            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForSaveOrOpen(JButton save, AbstractTableModel tableModel) {
        save.addActionListener(event -> {
            try {
                FileChooserTest.main(one, f -> {
                    one = f;
                    refreshFirst(one, tableModel, 1);
                });

            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByTable(JButton button, AbstractTableModel tableModel) {
        button.addActionListener(event -> {
            try {
                MyFrame.main(f -> {
                    one = f;
                    refreshFirst(one, tableModel, 1);
                });
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByTable(JButton button, AbstractTableModel tableModel, int k) {
        button.addActionListener(event -> {
            try {
                MyFrame.main(f -> {
                    two = f;
                    refreshFirst(two, tableModel, 1);
                });
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByFunc(JButton button, AbstractTableModel tableModel) {
        button.addActionListener(event -> {
            try {
                MathFunctionWindow.main(f -> {
                    one = f;
                    refreshFirst(one, tableModel, 1);
                });
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerCreateByFunc(JButton button, AbstractTableModel tableModel, int k) {
        button.addActionListener(event -> {
            try {
                MathFunctionWindow.main(f -> {
                    two = f;
                    refreshFirst(two, tableModel, 2);
                });
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void refreshFirst(TabulatedFunction myFunction, AbstractTableModel tableModel, int k) {

        ((MyTableModel2) tableModel).myFunction = myFunction;
        tableModel.fireTableDataChanged();
    }

    public void clearTable(int n, AbstractTableModel tableModel, int k) {
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                xOne.remove(n - i - 1);
                yOne.remove(n - i - 1);
                tableModel.fireTableDataChanged();
            }
        }
        if (k == 2) {
            for (int i = 0; i < n; i++) {
                xTwo.remove(n - i - 1);
                yTwo.remove(n - i - 1);
                tableModel.fireTableDataChanged();
            }
        }
        if (k == 3) {
            for (int i = 0; i < n; i++) {
                xThree.remove(n - i - 1);
                yThree.remove(n - i - 1);
                tableModel.fireTableDataChanged();
            }
        }
    }
}
