package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.AbstractTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SettingWindow extends JDialog {
    private JLabel fontLabel = new JLabel("Выберите тип фабрики функции:");
    private Map<String, TabulatedFunctionFactory> nameFuncMap = new HashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JButton buttonOk = new JButton("OK");
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setModal(true);
        this.factory = factory;
        setTitle("Настройка");
        setSize(300, 100);
        fillMap();
        compose();
        addButtonListeners();
    }

    public static void main(TabulatedFunctionFactory factory) {
        SettingWindow frame = new SettingWindow(factory);
        frame.setVisible(true);
    }

    public void compose() {
        setContentPane(new BgPanel());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(fontLabel)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(buttonOk))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(fontLabel)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(buttonOk)
                ));
    }

    public void fillMap() {
        nameFuncMap.put("Двусвязный список", new LinkedListTabulatedFunctionFactory());
        nameFuncMap.put("Массив", new ArrayTabulatedFunctionFactory());
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
        buttonOk.addActionListener(event -> {
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