package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.*;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MathFunctionWindow extends JDialog {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("От:");
    private JLabel toLabel = new JLabel("До:");
    private JLabel countLabel = new JLabel("Количество:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton buttonOk = new JButton("OK");
    private Map<String, MathFunction> nameFuncMap = new HashMap<>();
    TabulatedFunctionFactory factory;
    TabulatedFunction myFunction;

    public MathFunctionWindow(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        setTitle("Создание математической функции");
        this.factory = factory;
        this.setBounds(300, 200, 500, 150);
        fillMap();
        compose();
        addButtonListeners(callback);
    }

    public static void main(TabulatedFunctionFactory factory, Consumer<? super TabulatedFunction> callback) {
        MathFunctionWindow app = new MathFunctionWindow(factory, callback);
        app.setVisible(true);
    }

    public void fillMap() {
        nameFuncMap.put("Функция гиперболического косинуса", new CoshFunction());
        nameFuncMap.put("Тождественная функция", new IdentityFunction());
        nameFuncMap.put("Обратная функция", new ReverseFunction());
        nameFuncMap.put("Квадратичная функция", new SqrFunction());
        nameFuncMap.put("Единичная функция", new UnitFunction());
        nameFuncMap.put("Нулевая функция", new ZeroFunction());
        String[] functions = new String[6];
        int i = 0;
        for (String string : nameFuncMap.keySet()) {
            functions[i++] = string;
        }
        Arrays.sort(functions);
        for (String string : functions) {
            functionComboBox.addItem(string);
        }
    }

    public void compose() {
        setContentPane(new BgPanel());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonOk)
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(fromLabel)
                        .addComponent(fromField)
                        .addComponent(toLabel)
                        .addComponent(toField)
                        .addComponent(countLabel)
                        .addComponent(countField))
                .addComponent(functionComboBox)
                .addComponent(buttonOk)
        );
    }

    public void addButtonListeners(Consumer<? super TabulatedFunction> callback) {
        buttonOk.addActionListener(event -> {
            try {
                String function = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFuncMap.get(function);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                myFunction = factory.create(selectedFunction, from, to, count);
                callback.accept(myFunction);
                this.dispose();
            } catch (Exception e) {
                Error errorWindow = new Error(this, e);
                errorWindow.showError(this, e);
            }
        });
    }
}


