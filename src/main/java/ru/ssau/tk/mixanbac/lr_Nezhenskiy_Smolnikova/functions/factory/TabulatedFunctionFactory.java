package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.factory;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.MathFunction;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);
}

