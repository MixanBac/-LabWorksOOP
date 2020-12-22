package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;

public class Writer extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JButton save = new JButton("Сохранить");
    private TabulatedFunction func;

    public Writer(TabulatedFunction func) {
        setModal(true);
        this.func = func;
        JPanel panel = new JPanel();
        addListenerForSaveButton();
        panel.add(save);
        Container contentPan = getContentPane();
        contentPan.add(panel, BorderLayout.SOUTH);
        dir.setEditable(false);
        filename.setEditable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(filename);
        panel.add(dir);
        contentPan.add(panel, BorderLayout.NORTH);
    }

    public void addListenerForSaveButton() {
        save.addActionListener(event -> {
            JFileChooser c = new JFileChooser();
            c.setFileSelectionMode(JFileChooser.FILES_ONLY);
            c.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            c.setAcceptAllFileFilterUsed(false);
            int rVal = c.showSaveDialog(Writer.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
                File file = c.getSelectedFile();
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, func);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                filename.setText("Вы нажали отменить");
                dir.setText("");
            }
        });
    }

    public static void main(TabulatedFunction func) {
        run(new Writer(func), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
