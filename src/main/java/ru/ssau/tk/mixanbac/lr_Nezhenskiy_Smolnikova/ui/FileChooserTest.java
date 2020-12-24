package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.util.function.Consumer;

public class FileChooserTest extends JFrame {
    private final JTextField filename = new JTextField();
    private final JTextField dir = new JTextField();
    private final JButton open = new JButton("Открыть");
    private final JButton save = new JButton("Сохранить");
    private TabulatedFunction myFunction;
    private TabulatedFunctionFactory factory;

    public FileChooserTest(TabulatedFunction myFunction) {
        this.myFunction = myFunction;
        JPanel panel = new JPanel();

        addListenerForSaveButton(myFunction);
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

    public FileChooserTest(TabulatedFunction result, Consumer<? super TabulatedFunction> callback) {
        JPanel panel = new JPanel();
        addListenerForOpenButton(callback);
        panel.add(open);
        addListenerForSaveButton(result);
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

    public void addListenerForSaveButton(TabulatedFunction result) {
        save.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int showSaveDialog = fileChooser.showSaveDialog(FileChooserTest.this);
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, result);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (showSaveDialog == JFileChooser.CANCEL_OPTION) {
                filename.setText("Вы нажали отменить");
                dir.setText("");
            }
        });
    }

    /*public void addListenerForOpenButton() {
        open.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int showSaveDialog = fileChooser.showOpenDialog(FileChooserTest.this);
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                factory = new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        myFunction = FunctionsIO.readTabulatedFunction(inputStream, factory);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (showSaveDialog == JFileChooser.CANCEL_OPTION) {
                filename.setText("Вы нажали отменить");
                dir.setText("");
            }
        });
    }*/

    public void addListenerForOpenButton(Consumer<? super TabulatedFunction> callback) {
        open.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Текстовые файлы", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int rVal = fileChooser.showOpenDialog(FileChooserTest.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                factory = new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        myFunction = FunctionsIO.readTabulatedFunction(inputStream, factory);
                        callback.accept(myFunction);
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

    public static void main(TabulatedFunction result, Consumer<? super TabulatedFunction> callback) {
        run(new FileChooserTest(result, callback), 250, 100);
    }

    public static void main(TabulatedFunction myFunction) {
        run(new FileChooserTest(myFunction), 250, 100);
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}