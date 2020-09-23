package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

public class ConstantFunction implements MathFunction {
    private final double constanta;

    public ConstantFunction(double constanta) {
        this.constanta = constanta;
    }

    @Override
    public double apply(double x) {
        return constanta;
    }

    public double getConstanta() {
        return constanta;
    }
}
