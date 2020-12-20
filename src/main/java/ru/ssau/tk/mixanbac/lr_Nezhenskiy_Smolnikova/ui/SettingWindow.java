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
    JLabel fontLabel = new JLabel("Which factory do you want to use?");
    private Map<String, AbstractTabulatedFunction> nameFunctionMap = new HashMap<>();
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JButton okButton = new JButton("OK");
    TabulatedFunctionFactory factory;

    public SettingWindow(TabulatedFunctionFactory factory) {
        setModal(true);
        setTitle("Settings");
        this.factory = factory;
        setSize(500, 500);
        fillMap();
        compose();
    }
    public SettingWindow() {
        setModal(true);
        setTitle("Settings");
        factory = new ArrayTabulatedFunctionFactory();
        setSize(300, 200);
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

    public static void main(TabulatedFunctionFactory factory) {
        SettingWindow dialog = new SettingWindow(factory);
        dialog.setVisible(true);
    }
        public static void main( String[] args) {
            SettingWindow dialog = new SettingWindow();
            dialog.setVisible(true);
        }

}





