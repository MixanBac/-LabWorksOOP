package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        try (BufferedOutputStream listOut = new BufferedOutputStream(new FileOutputStream("output/linked list function.bin"));
             BufferedOutputStream arrayOut = new BufferedOutputStream(new FileOutputStream("output/binary function.bin"))) {
            double[] xValues = new double[]{1., 2., 3., 4., 5., 6., 7.};
            double[] yValues = new double[]{6., 7., 8., 9., 10., 11., 12.};
            ArrayTabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            LinkedListTabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction arrayFunction = arrayFactory.create(xValues, yValues);
            TabulatedFunction linkedListFunction = linkedListFactory.create(xValues, yValues);
            FunctionsIO.writeTabulatedFunction(arrayOut, arrayFunction);
            FunctionsIO.writeTabulatedFunction(listOut, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

