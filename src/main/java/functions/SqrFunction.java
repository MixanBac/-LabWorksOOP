package functions;

public class SqrFunction implements MathFunction{

    public SqrFunction(double x){
    }

    @Override
    public MathFunction apply(double x) {
        return new SqrFunction (Math.pow(x,2));
    }

}
