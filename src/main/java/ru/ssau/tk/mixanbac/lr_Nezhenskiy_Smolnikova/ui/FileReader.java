package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.function.Consumer;

public class FileReader extends JDialog {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JButton open = new JButton("Открыть");
    private TabulatedFunction func;
    private TabulatedFunctionFactory factory;

    public FileReader(Consumer<? super TabulatedFunction> callback) {
        setModal(true);
        JPanel panel = new JPanel();
        addListenerForOpenButton(callback);
        panel.add(open);
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

    public void addListenerForOpenButton(Consumer<? super TabulatedFunction> callback) {
        open.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(FileReader.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                factory = new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        func = FunctionsIO.readTabulatedFunction(inputStream, factory);
                        callback.accept(func);
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

    public static void main(Consumer<? super TabulatedFunction> callback) {
        run(new FileReader(callback), 250, 110);
    }

    public static void run(JDialog frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
