package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import javax.swing.*;

public class Menu extends JFrame {
    private JFrame frame;
    private JButton okButton = new JButton("Start");
    private JButton inputButton = new JButton("OneOkno");

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
                    MyFrame mainWindow = new MyFrame(frame);
                    mainWindow.setVisible(true);
                    frame.setVisible(false);
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
                        .addComponent(okButton)
                        .addComponent(inputButton))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(okButton)
                        .addComponent(inputButton))
        );
    }
}

