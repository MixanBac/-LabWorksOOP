package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.module.ModuleFinder.compose;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton inputButton = new JButton("Create the table");
    private JButton inputButtonFactory = new JButton("Choose factory");
    private JButton inputButtonMath = new JButton("Choose Math function");
    private JButton inputButtonCalc = new JButton("Calculation");
    private TableModel tableModel = new MyTableModel();
    private TabulatedFunctionFactory factory;
    TabulatedFunction myFunction;

    public Menu() throws IOException {
        setTitle("Menu");
        setBounds(500, 500, 500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionPerformed();
        compose();
        this.factory = new ArrayTabulatedFunctionFactory();
    }


    public void actionPerformed() {
        inputButton.addActionListener(event -> {
                    try {
                        MyFrame.main( new String[]{});

                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
        );

        inputButtonFactory.addActionListener(event ->

        {
            try {
                SettingWindow.main(factory);
            } catch (Exception e) {
                new Error(this, e);
            }
        });

        inputButtonMath.addActionListener(event ->

        {
            try {
                MathFunctionWindow.main(new String[]{});
            } catch (Exception e) {
                new Error(this, e);
            }
        });
        inputButtonCalc.addActionListener(event ->
        {
            try {
                CalculationWindow.main(frame);
            } catch (Exception e) {
                new Error(this, e);
            }
        });
    }


    void compose() {
        setContentPane(new BgPanel());
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(inputButton)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(inputButton)
                        .addComponent(inputButtonMath)
                        .addComponent(inputButtonCalc)
                        .addComponent(inputButtonFactory))
        );
    }

    public static void main(String[] args) throws IOException {
        Menu window = new Menu();
        window.setVisible(true);
    }
}
class BgPanel extends JPanel {
    public void paintComponent(Graphics g) {
        Image im = null;
        try {
            im = ImageIO.read(new File("C:\\Users\\1\\Desktop\\games-4952694_960_720.jpg"));
        } catch (IOException ignored) {
        }
        g.drawImage(im, 0, 0, null);
    }
        }





