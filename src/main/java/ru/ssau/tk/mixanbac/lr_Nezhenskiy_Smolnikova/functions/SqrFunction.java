package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

public class SqrFunction implements MathFunction{

    @Override
    public double apply(double x) {
        return java.lang.Math.pow(x,2);
    }

}
