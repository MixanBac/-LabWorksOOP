package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.ArrayTabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        File fileArray = new File("output/serialized array functions.bin");
        double[] x = {1, 2, 3, 4, 5};
        double[] y = {6, 7, 8, 9, 10};

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(new ArrayTabulatedFunctionFactory());
        TabulatedFunction arrayFunction1 = new ArrayTabulatedFunction(x, y);
        TabulatedFunction arrayFunction2 = differentialOperator.derive(arrayFunction1);
        TabulatedFunction arrayFunction3 = differentialOperator.derive(arrayFunction2);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileArray));
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileArray))) {

            FunctionsIO.serialize(out, arrayFunction1);
            FunctionsIO.serialize(out, arrayFunction2);
            FunctionsIO.serialize(out, arrayFunction3);

            TabulatedFunction resultArray1 = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray2 = FunctionsIO.deserialize(in);
            TabulatedFunction resultArray3 = FunctionsIO.deserialize(in);

            System.out.println(resultArray1.toString());
            System.out.println(resultArray2.toString());
            System.out.println(resultArray3.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
