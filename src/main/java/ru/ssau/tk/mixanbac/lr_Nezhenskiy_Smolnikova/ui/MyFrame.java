package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MyFrame extends JDialog {

    private final List<Double> xValues = new ArrayList<>();
    private final List<Double> yValues = new ArrayList<>();
    private final AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    private final JTable table = new JTable(tableModel);
    private final JLabel label = new JLabel("Введите количество точек:");
    private final JTextField countField = new JTextField();
    private final JButton inputButton = new JButton("Ввести");
    private final JButton commitButton = new JButton("Сохранить");
    private final TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private TabulatedFunction func;

    public static void main(String[] args) {
        MyFrame app = new MyFrame();
        app.setVisible(true);
    }

    public static void main(Consumer<? super TabulatedFunction> callback) {
        MyFrame app = new MyFrame(callback);
        app.setVisible(true);
    }

    public static void main(TabulatedFunction func) {
        MyFrame app = new MyFrame(func);
        app.setVisible(true);
    }

    public MyFrame() {
        setModal(true);
        setTitle("Создание таблицы");
        this.setBounds(300, 300, 500, 500);
        addButtonListeners();
        compose();
        inputButton.setEnabled(false);
        commitButton.setEnabled(false);
    }

    public MyFrame(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        setTitle("Мы не молодцы");
        this.setBounds(500, 200, 500, 500);
        addButtonListeners(callback);
        compose();
        inputButton.setEnabled(false);
        commitButton.setEnabled(false);
    }

    public MyFrame(TabulatedFunction func) {
        setModal(true);
        setTitle("Мы не молодцы");
        this.func = func;
        this.setBounds(300, 300, 500, 500);
        addButtonListeners();
        compose();
        inputButton.setEnabled(false);
        commitButton.setEnabled(false);
    }



    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        JScrollPane tableScrollPane = new JScrollPane(table);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(commitButton)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(label)
                        .addComponent(countField)
                        .addComponent(inputButton))
                .addComponent(tableScrollPane)
                .addComponent(commitButton)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        addListenerForInputButton();
        addListenerForCommitButton(callback);
        addListenerForCountButton();
    }

    public void addButtonListeners() {
        addListenerForInputButton();
        addListenerForCommitButton();
        addListenerForCountButton();
    }

    public void clearTable(int n) {
        for (int i = 0; i < n; i++) {
            xValues.remove(n - i - 1);
            yValues.remove(n - i - 1);
            tableModel.fireTableDataChanged();
        }
    }

    public void addListenerForInputButton() {
        inputButton.addActionListener(event -> {
            try {
                commitButton.setEnabled(false);
                int count = Integer.parseInt(countField.getText());
                clearTable(tableModel.getRowCount());
                for (int i = 0; i < count; i++) {
                    xValues.add(0.);
                    yValues.add(0.);
                    tableModel.fireTableDataChanged();
                }
                if (tableModel.getRowCount() > 1) {
                    commitButton.setEnabled(true);
                }
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForCommitButton(Consumer<? super TabulatedFunction> callback) {
        commitButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                x[0] = xValues.get(0);
                y[0] = yValues.get(0);
                for (int i = 1; i < xValues.size(); i++) {
                    if (xValues.get(i - 1) > xValues.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                func = factory.create(x, y);
                callback.accept(func);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForCommitButton() {
        commitButton.addActionListener(event -> {
            try {
                double[] x = new double[xValues.size()];
                double[] y = new double[xValues.size()];
                x[0] = xValues.get(0);
                y[0] = yValues.get(0);
                for (int i = 1; i < xValues.size(); i++) {
                    if (xValues.get(i - 1) > xValues.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                    x[i] = xValues.get(i);
                    y[i] = yValues.get(i);
                }
                func = factory.create(x, y);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }

    public void addListenerForCountButton() {
        countField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                onChanged();
            }

            private void onChanged() {
                inputButton.setEnabled(!countField.getText().isEmpty());
            }
        });
    }
}
