package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.ArrayIsNotSortedException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame {
    private static final int SPACING_SIZE = 10;
    private JFrame jFrame = new JFrame("This is frame");
    List<String> xValues = new ArrayList<>();
    List<String> yValues = new ArrayList<>();
    AbstractTableModel tableModel = new MyTableModel(xValues, yValues);
    JTable table = new JTable(tableModel);
    private JLabel label = new JLabel("Input number of points:");
    private JTextField countField = new JTextField();
    private JButton inputButton = new JButton("Input");
    private JButton commitButton = new JButton("Commit");
    //private LinkedHashMap<Double, Double> map = new LinkedHashMap<>();
    List<Double> xValuesDouble = new ArrayList<>();
    List<Double> yValuesDouble = new ArrayList<>();

    public static void main(String[] args) {
        MyFrame app = new MyFrame();
        app.setVisible(true);
    }

    public MyFrame() {
        super("Ну чё, мы сдали?");
        this.setBounds(300, 300, 500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addButtonListeners();
        compose();
        inputButton.setEnabled(false);
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
        inputButton.addActionListener(event -> {
            try {
                int count = Integer.parseInt(countField.getText());
                if (tableModel.getRowCount() > 0) {
                    int n = tableModel.getRowCount();
                    for (int i = 0; i < n; i++) {
                        xValues.remove(n - i - 1);
                        yValues.remove(n - i - 1);
                        tableModel.fireTableDataChanged();
                    }
                }
                //table = new JTable(new MyTableModel(new ArrayList<>(count), new ArrayList<>(count)));
                for (int i = 0; i < count; i++) {
                    xValues.add("");
                    yValues.add("");
                    tableModel.fireTableDataChanged();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getStackTrace());
            }
        });

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

        commitButton.addActionListener(event -> {
            try {
                if (xValuesDouble.size() > 0) {
                    xValuesDouble.clear();
                    yValuesDouble.clear();
                }
                int n = tableModel.getRowCount();
                //map = new LinkedHashMap<Double, Double>(n);
                for (int i = 0; i < n; i++) {
                    xValuesDouble.add(Double.parseDouble(tableModel.getValueAt(i, 1).toString()));
                    yValuesDouble.add(Double.parseDouble(tableModel.getValueAt(i, 2).toString()));
                    //map.put(Double.parseDouble(tableModel.getValueAt(i, 1).toString()), Double.parseDouble(tableModel.getValueAt(i, 2).toString()));
                }
                for (int i = 1; i < xValuesDouble.size(); i++) {
                    if (xValuesDouble.get(i - 1) > xValuesDouble.get(i)) {
                        throw new ArrayIsNotSortedException();
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getStackTrace());
            }
        });
    }
}
