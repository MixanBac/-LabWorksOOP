package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import javax.swing.*;

import static com.sun.org.apache.xerces.internal.util.DOMUtil.setVisible;

public class Panel extends JFrame {
    private JTextField textField1;
    private JPanel panel1;
    private JButton button1;
    public Panel() {
        setContentPane(panel1);
        setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Panel();
    }
}
