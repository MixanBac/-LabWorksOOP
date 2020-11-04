package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction>  {
    T derive(T function);
}
