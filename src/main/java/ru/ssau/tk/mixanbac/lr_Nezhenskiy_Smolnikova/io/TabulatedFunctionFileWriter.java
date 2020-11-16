package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        File fileArray = new File("output/array function.txt");
        File fileList = new File("output/linked list function.txt");

        double[] xValue = new double[]{1, 2, 3, 4, 5};
        double[] yValue = new double[]{6, 7, 8, 9, 10};

        TabulatedFunction functionList = new LinkedListTabulatedFunction(xValue, yValue);
        TabulatedFunction functionArray = new ArrayTabulatedFunction(xValue, yValue);

        try (BufferedWriter outArray = new BufferedWriter(
                new FileWriter(fileArray));
             BufferedWriter outList = new BufferedWriter(
                     new FileWriter(fileList))) {

            FunctionsIO.writeTabulatedFunction(outArray, functionArray);
            FunctionsIO.writeTabulatedFunction(outList, functionList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
