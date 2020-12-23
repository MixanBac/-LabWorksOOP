package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;

public class FileWriter extends JDialog {
    private JTextField fname = new JTextField();
    private JTextField dir = new JTextField();
    private JButton save = new JButton("Сохранить");
    private TabulatedFunction function;

    public FileWriter(TabulatedFunction func) {
        setModal(true);
        this.function = function;
        JPanel panel = new JPanel();
        addListenerForSaveButton();
        panel.add(save);
        Container contentPane = getContentPane();
        contentPane.add(panel, BorderLayout.SOUTH);
        dir.setEditable(false);
        fname.setEditable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        panel.add(fname);
        panel.add(dir);
        contentPane.add(panel, BorderLayout.NORTH);
    }

    public void addListenerForSaveButton() {
        save.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showSaveDialog(FileWriter.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                fname.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, function);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (rVal == JFileChooser.CANCEL_OPTION) {
                fname.setText("Вы отменили операцию");
                dir.setText("");
            }
        });
    }

    public static void main(TabulatedFunction func) {
        run(new FileWriter(func), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
