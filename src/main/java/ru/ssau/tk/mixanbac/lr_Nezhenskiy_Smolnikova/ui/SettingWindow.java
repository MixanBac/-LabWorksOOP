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

public class SettingWindow extends JFrame {
    JLabel fontLabel = new JLabel("Which factory do you want to use?");
    private Map<String, AbstractTabulatedFunction> nameFunctionMap = new HashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();

    private JButton okButton = new JButton("OK");
    private JComboBox fontComboBox;

    public SettingWindow() {
        setTitle("Settings");
        setSize(500, 500);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        fillMap();
        compose();
    }

    public void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(functionComboBox)
                        .addComponent(okButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(functionComboBox)
                        .addComponent(okButton)
                ));
    }

    public void fillMap() {
        nameFunctionMap.put("List", new LinkedListTabulatedFunction());
        nameFunctionMap.put("Array", new ArrayTabulatedFunction());
        String[] functions = new String[2];
        int i = 0;
        for (String string : nameFunctionMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }
    public static void main(JFrame args) {
        JFrame frame = new SettingWindow();
        frame.setVisible(true);
    }
}

