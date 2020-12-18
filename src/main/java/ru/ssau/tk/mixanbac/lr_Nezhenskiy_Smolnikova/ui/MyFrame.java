package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {
    private static final int SPACING_SIZE = 10;
    List<Double> xValues = new ArrayList<>();
    List<Double> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    JTable table = new JTable(tableModel);
    private JLabel label = new JLabel("Input number of points:");
    private JTextField countField = new JTextField();
    private JButton inputButton = new JButton("Input");
    private JButton commitButton = new JButton("Commit");
    public static void main(String[] args) {
        MyFrame app = new MyFrame();
        app.setVisible(true);
    }
    public MyFrame() {
        super("Ну чё, мы сдали?");
        this.setBounds(500, 500, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addButtonListeners();
        compose();
        inputButton.setEnabled(false);
        commitButton.setEnabled(false);

    }
    public MyFrame(JFrame parent) {
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
                LinkedListTabulatedFunction myFunction = new LinkedListTabulatedFunction(x, y);
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

