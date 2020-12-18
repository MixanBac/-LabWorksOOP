package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static java.lang.module.ModuleFinder.compose;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButton = new JButton("Create the table");
    private JButton inputButtonFactory = new JButton("Choose factory");
    private JButton inputButtonMath = new JButton("Choose Math function");

    public Menu() {
        setTitle("Menu");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();

    }

    public static void main(String[] args) {
        Menu window = new Menu();
        window.setVisible(true);
    }


    public void actionPerformed() {
        inputButton.addActionListener(event -> {
            try {
                MyFrame mainWindow = new MyFrame(frame);
                mainWindow.setVisible(true);
                frame.setVisible(false);
            } catch (Exception e) {
                new Error(this, e);
            }
                }
        );
    }

    void compose() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButton))
        );
    }
}


