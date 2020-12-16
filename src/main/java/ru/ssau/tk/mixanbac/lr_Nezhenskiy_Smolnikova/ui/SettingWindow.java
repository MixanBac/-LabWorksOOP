package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.AbstractTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingWindow extends JDialog {
    private JLabel fontLabel = new JLabel("Which factory do you want to use?");
    private Map<String, TabulatedFunctionFactory> nameFuncMap = new HashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();

    private JButton okButton = new JButton("OK");
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setModal(true);
        this.factory = factory;
        setTitle("Settings");
        setSize(500, 500);
        fillMap();
        compose();
        addButtonListeners();
    }

    public static void main(TabulatedFunctionFactory factory) {
        SettingWindow frame = new SettingWindow(factory);
        frame.setVisible(true);
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
    }

    public void fillMap() {
        nameFuncMap.put("List", new LinkedListTabulatedFunctionFactory());
        nameFuncMap.put("Array", new ArrayTabulatedFunctionFactory());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFuncMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }
    public void addButtonListeners() {
        okButton.addActionListener(event -> {
            try {
                String func = (String) functionComboBox.getSelectedItem();
                this.factory = nameFuncMap.get(func);
                this.dispose();
            } catch (Exception e) {
                Error errorWindow = new Error(this, e);
                errorWindow.showError(this, e);
            }
        });
    }
}

