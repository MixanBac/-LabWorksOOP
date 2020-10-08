package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    private final static double DELTA = 0.00001;

    private final double[] xValues1 = new double[]{1, 2, 3, 4, 5};
    private final double[] yValues1 = new double[]{6, 7, 8, 9, 10};

    private final double[] xValues2 = new double[]{11, 12, 13, 14, 15};
    private final double[] yValues2 = new double[]{16, 17, 18, 19, 20};

    @Test
    public void testApply() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction coshFunction = new CoshFunction();
        MathFunction doubleIdentityFunction = new CompositeFunction(identityFunction, identityFunction);
        assertEquals(doubleIdentityFunction.apply(1), 1, DELTA);

        MathFunction identityCoshFunction = new CompositeFunction(identityFunction, coshFunction);
        assertEquals(identityCoshFunction.apply(1), 1.54308, DELTA);

        MathFunction sqrFunction = new SqrFunction();
        MathFunction unitFunction = new UnitFunction();
        MathFunction sqrCoshUnitFunction = sqrFunction.andThen(coshFunction).andThen(unitFunction);
        assertEquals(sqrCoshUnitFunction.apply(4), 1, DELTA);
        assertNotEquals(sqrCoshUnitFunction.apply(1), 0, DELTA);
        assertEquals(sqrCoshUnitFunction.apply(1651), 1, DELTA);

        double result = coshFunction.andThen(sqrFunction).andThen(identityFunction).apply(100);
        assertEquals(result, 1.8064934420314375E86, DELTA);
        assertNotEquals(result, 100, DELTA);
        assertNotEquals(result, 1, DELTA);

        MathFunction listFunction = new LinkedListTabulatedFunction(xValues1, yValues1);
        MathFunction arrayFunction = new ArrayTabulatedFunction(xValues2, yValues2);
        MathFunction arrayListSqrFunction = arrayFunction.andThen(listFunction).andThen(sqrFunction);
        assertEquals(arrayListSqrFunction.apply(1), 121, DELTA);
        assertEquals(arrayListSqrFunction.apply(2.55), 157.5025, DELTA);
        assertEquals(arrayListSqrFunction.apply(44), 2916, DELTA);
    }
}
