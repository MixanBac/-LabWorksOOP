package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

public class CoshFunction implements MathFunction{

    @Override
    public double apply(double x) {
        return java.lang.Math.cosh(x);
    }

}

