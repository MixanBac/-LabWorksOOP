package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/serialized linked list functions.bin"))) {
            final double[] x = {1, 2, 3, 4, 5};
            final double[] y = {6, 7, 8, 9, 10};
            final TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
            TabulatedFunction newFunction = new LinkedListTabulatedFunction(x, y);
            TabulatedFunction onceDerivedFunction = operator.derive(newFunction);
            TabulatedFunction twiceDerivedFunction = operator.derive(onceDerivedFunction);
            FunctionsIO.serialize(out, newFunction);
            FunctionsIO.serialize(out, onceDerivedFunction);
            FunctionsIO.serialize(out, twiceDerivedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("output/serialized linked list functions.bin"))) {

            TabulatedFunction function = FunctionsIO.deserialize(in);
            TabulatedFunction onceDerivedFunction = FunctionsIO.deserialize(in);
            TabulatedFunction twiceDerivedFunction = FunctionsIO.deserialize(in);
            System.out.println(function.toString());
            System.out.println(onceDerivedFunction.toString());
            System.out.println(twiceDerivedFunction.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
