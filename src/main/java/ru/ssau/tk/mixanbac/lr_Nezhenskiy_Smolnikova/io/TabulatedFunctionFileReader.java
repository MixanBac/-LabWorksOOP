package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.io;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory.LinkedListTabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        File myFile = new File("input/function.txt");
        try (BufferedReader inArray = new BufferedReader(new FileReader(myFile));
             BufferedReader inList = new BufferedReader(new FileReader(myFile))) {
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(inArray, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunction.toString());

            TabulatedFunction listFunction = FunctionsIO.readTabulatedFunction(inList, new LinkedListTabulatedFunctionFactory());
            System.out.println(listFunction.toString());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
