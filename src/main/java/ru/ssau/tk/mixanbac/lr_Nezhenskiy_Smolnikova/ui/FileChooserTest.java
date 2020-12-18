package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.ui;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.AbstractTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io.FunctionsIO;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;

public class FileChooserTest extends JFrame {
    private JTextField filename = new JTextField();
    private JTextField dir = new JTextField();
    private JButton open = new JButton("Open");
    private JButton save = new JButton("Save");
    private AbstractTabulatedFunction myFunction;
    private TabulatedFunctionFactory factory;

    public FileChooserTest() {
        JPanel panel = new JPanel();
        addListenerForOpenButton();
        panel.add(open);
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
            JFileChooser fileChooser = new JFileChooser();
            // Demonstrate "Save" dialog:
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Text files", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int showSaveDialog = fileChooser.showSaveDialog(FileChooserTest.this);
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                myFunction = new ArrayTabulatedFunction(new double[]{1, 2}, new double[]{2, 3});
                if (file != null) {
                    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                        FunctionsIO.writeTabulatedFunction(outputStream, myFunction);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (showSaveDialog == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        });
    }

    public void addListenerForOpenButton() {
        open.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser();
            // Demonstrate "Open" dialog:
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.addChoosableFileFilter(
                    new FileNameExtensionFilter("Text files", "txt"));
            fileChooser.setAcceptAllFileFilterUsed(false);
            int showSaveDialog = fileChooser.showOpenDialog(FileChooserTest.this);
            if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
                filename.setText(fileChooser.getSelectedFile().getName());
                dir.setText(fileChooser.getCurrentDirectory().toString());
                File file = fileChooser.getSelectedFile();
                factory=new ArrayTabulatedFunctionFactory();
                if (file != null) {
                    try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                        var func1 = FunctionsIO.readTabulatedFunction(inputStream, factory);
                    } catch (Exception e) {
                        new Error(this, e);
                    }
                }
            }
            if (showSaveDialog == JFileChooser.CANCEL_OPTION) {
                filename.setText("You pressed cancel");
                dir.setText("");
            }
        });
    }

    public static void main(String[] args) {
        run(new FileChooserTest(), 250, 110);
    }

    public static void run(JFrame frame, int width, int height) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setVisible(true);
    }
}
