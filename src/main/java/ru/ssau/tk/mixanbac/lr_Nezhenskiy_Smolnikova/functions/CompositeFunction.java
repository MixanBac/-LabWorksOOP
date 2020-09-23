package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

public class CompositeFunction  implements MathFunction {
    private MathFunction firstFunction ;
    private MathFunction secondFunction;
    public CompositeFunction(MathFunction firstFunction1, MathFunction secondFunction1) {
        firstFunction = firstFunction1;
        secondFunction = secondFunction1;
    }
    @Override
    public double apply(double x) {
            x= firstFunction.apply(secondFunction.apply(x));
        return x;
        }
    }

