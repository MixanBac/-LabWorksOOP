package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.*;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MathFunctionWindow extends JFrame {
    private JComboBox<String> functionComboBox = new JComboBox<>();
    private JLabel fromLabel = new JLabel("from:");
    private JLabel toLabel = new JLabel("to:");
    private JLabel countLabel = new JLabel("count:");
    private JTextField countField = new JTextField();
    private JTextField fromField = new JTextField();
    private JTextField toField = new JTextField();
    private JButton buttonOk = new JButton("OK");
    private Map<String, MathFunction> nameFuncMap = new HashMap<>();
    TabulatedFunction function;

    public static void main(JFrame args) {
        MathFunctionWindow app = new MathFunctionWindow();
        app.setVisible(true);
    }

    public static void main(TabulatedFunction myFunction) {
        MathFunctionWindow app = new MathFunctionWindow(myFunction);
        app.setVisible(true);
    }

    public MathFunctionWindow(TabulatedFunction function) {
        super("CreateFunc");
        this.function = function;
        this.setBounds(300, 200, 500, 200);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fillMap();
        compose();
        addButtonListeners();
    }

    public MathFunctionWindow() {
        super("CreateFunc");
        this.setBounds(300, 200, 500, 200);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fillMap();
        compose();
        addButtonListeners();
    }

    public void fillMap() {
        nameFuncMap.put("cosh", new CoshFunction());
        nameFuncMap.put("reverse", new ReverseFunction());
        nameFuncMap.put("sqr", new SqrFunction());
        nameFuncMap.put("unit", new UnitFunction());
        nameFuncMap.put("zero", new ZeroFunction());
        String[] functions = new String[5];
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

    public void addButtonListeners() {
        buttonOk.addActionListener(event -> {
            try {
                String myFunction = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = nameFuncMap.get(myFunction);
                double from = Double.parseDouble(fromField.getText());
                double to = Double.parseDouble(toField.getText());
                int count = Integer.parseInt(countField.getText());
                function = new ArrayTabulatedFunction(selectedFunction, from, to, count);
                int k=1;
            } catch (Exception e) {
                Error myError = new Error(this, e);
                myError.showError(this, e);
            }
        });
    }
}

