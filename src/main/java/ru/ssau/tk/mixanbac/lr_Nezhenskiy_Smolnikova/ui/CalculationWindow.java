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
    public final JFrame frame = new JFrame();
    private final Map<String, Integer> nameFunctionMap = new LinkedHashMap<>();
    private final JComboBox<String> functionComboBox = new JComboBox<>();
    private final JButton calculate = new JButton("Калькулятор");
    private final TabulatedFunctionFactory factoryResult = new ArrayTabulatedFunctionFactory();
    private TabulatedFunctionFactory factoryFirst;
    private TabulatedFunction result = new ArrayTabulatedFunction();
    private TabulatedFunction one = new ArrayTabulatedFunction();
    private TabulatedFunction second = new ArrayTabulatedFunction();
    private AbstractTableModel tablemodel;
    private AbstractTableModel tablemodel1;
    private AbstractTableModel tablemodel2;
    private TabulatedFunctionFactory factory;
    private final MyTableModel1 tableModell = new MyTableModel1();
    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();

    public CalculationWindow() {
        setModal(true);
        setTitle("Калькулятор");
        this.setBounds(0, 100, 1600, 600);
        if (factoryFirst instanceof LinkedListTabulatedFunction) {
            TabulatedFunction one = new LinkedListTabulatedFunction();
        } else {
            TabulatedFunction one = new ArrayTabulatedFunction();

        }

        fillMap();
        compose();
        addListenerForCalculate();

    }

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
                BorderUIResource.LineBorderUIResource(Color.PINK, 5));
        AbstractTableModel tableModel = new MyTableModel2(one) {
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

        };
        this.tablemodel1 = tableModel;
        JLabel label = new JLabel("Первая");
        JTable table1 = new JTable(tableModel);
        JButton save1 = new JButton("Сохр");
        JButton open1 = new JButton("Откр");
        JButton createByArray = new JButton("Создать т");
        JButton createByFunc = new JButton("Создать ф");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        addListenerCreateByTable(createByArray, tableModel);
        panel.add(createByFunc);
        panel.add(save1);
        addListenerForSaveOrOpen(save1, tableModel);
        panel.add(open1);
        addListenerForSaveOrOpen(open1, tableModel);
        panel.setPreferredSize(new Dimension(3, 3));
        return panel;
    }

    public JPanel secondFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.PINK, 5));
        AbstractTableModel tableModel = new MyTableModel2(second) {
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

        };
        this.tablemodel2 = tableModel;
        JLabel label = new JLabel("Вторая");
        JTable table1 = new JTable(tableModel);
        JButton saveOrOpen = new JButton("Сохранить и открыть");
        JButton createByArray = new JButton("Создать т");
        JButton createByFunc = new JButton("Создать ф");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(createByArray);
        addListenerCreateByTable(createByArray, tableModel, 1);
        panel.add(createByFunc);
        panel.add(saveOrOpen);
        addListenerForSaveOrOpen(saveOrOpen, tableModel);
        panel.setPreferredSize(new Dimension(100, 150));
        return panel;
    }

    public JPanel resultFunc() {
        JPanel panel = new JPanel();
        panel.setBorder(new
                BorderUIResource.LineBorderUIResource(Color.PINK, 5));
        AbstractTableModel tableModel = new MyTableModel2(result) {
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

        };
        this.tablemodel = tableModel;
        JLabel label = new JLabel("Результат");
        JTable table1 = new JTable(tableModel);

        JButton save = new JButton("                        Сохранить                        ");
        JScrollPane tableScrollPane = new JScrollPane(table1);
        panel.add(label);
        panel.add(tableScrollPane);
        panel.add(save);
        panel.setPreferredSize(new Dimension(100, 150));
        return panel;
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
        CalculationWindow window = new CalculationWindow();
        window.setVisible(true);
    }

    public void addListenerForCalculate() {
        calculate.addActionListener(event -> {
            try {
                TabulatedFunctionOperationService operate = new TabulatedFunctionOperationService(factoryResult);
                String func = (String) functionComboBox.getSelectedItem();
                int selectedFunction = nameFunctionMap.get(func);
                switch (selectedFunction) {
                    case 1:
                        result = operate.sum(one, second);
                        break;
                    case 2:
                        result = operate.subtract(one, second);
                        break;
                    case 3:
                        result = operate.multiply(one, second);
                        break;
                    case 4:
                        result = operate.divide(one, second);
                        break;
                }
                refreshFirst(result, tablemodel, 3);
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
                    second = f;
                    refreshFirst(second, tableModel, 1);
                });
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void refreshFirst(TabulatedFunction func, AbstractTableModel tableModel, int k) {
        ((MyTableModel2) tableModel).func = func;
        tableModel.fireTableDataChanged();
    }

    public void wrapper(int countOld, int countNew) {
        tableModell.fireTableDataChanged();
        for (int i = 0; i < countOld; i++) {
            if (xValues.size() != 0) xValues.remove(countOld - i - 1);
            if (yValues.size() != 0) yValues.remove(countOld - i - 1);
        }
        for (int i = 0; i < countNew; i++) {
            xValues.add(tableModell.getFunction().getX(i));
            yValues.add(tableModell.getFunction().getY(i));
        }
    }
}

