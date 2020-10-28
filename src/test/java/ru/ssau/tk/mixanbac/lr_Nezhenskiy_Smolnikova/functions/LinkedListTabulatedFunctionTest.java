package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.exceptions.InterpolationException;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionTest {
    private final MathFunction source = new SqrFunction();
    private final static double DELTA = 0.001;
    private final MathFunction sqr = new SqrFunction();
    private final static double ACCURACY = 0.001;
    private final double[] xValues = new double[]{1.0, 1.1, 1.2, 1.3, 1.4};
    private final double[] yValues = new double[]{2.0, 2.1, 2.2, 2.3, 2.4};

    private LinkedListTabulatedFunction listFunction() {
        return new LinkedListTabulatedFunction(source, 1, 5, 5);
    }

    private LinkedListTabulatedFunction getListFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 5, 5);
    }

    private LinkedListTabulatedFunction getFunction() {
        return new LinkedListTabulatedFunction(sqr, 1, 5, 7);
    }

    @Test
    public void testGetCount() {
        assertEquals(listFunction().getCount(), 10, DELTA);
        assertEquals(getListFunction().getCount(), 10, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -1, 1, 1);
        assertEquals(getFunction().getCount(), 14);
        assertEquals(testFunction.getCount(), 1);
    }

    @Test
    public void testGetX() {
        assertEquals(listFunction().getX(1), 2, DELTA);
        assertEquals(getListFunction().getX(1), 2, ACCURACY);
        assertEquals(getFunction().getX(5), 4.3333, ACCURACY);
        assertEquals(getFunction().getX(1), 1.6666, ACCURACY);
    }


    @Test
    public void testGetY() {
        assertEquals(listFunction().getY(1), 4, DELTA);
        assertEquals(getListFunction().getY(1), 4, ACCURACY);
        assertEquals(getFunction().getY(1), 2.7777, ACCURACY);
        assertEquals(getFunction().getY(1), 2.7777, ACCURACY);
    }

    @Test
    public void testSetY() {
        listFunction().setY(2, 2);
        assertEquals(listFunction().getY(1), 4, DELTA);
        listFunction().setY(3, 3);
        LinkedListTabulatedFunction function = getFunction();
        LinkedListTabulatedFunction someFunction = getListFunction();
        someFunction.setY(1, 2);
        assertEquals(someFunction.getY(1), 2, ACCURACY);
        function.setY(2, 2);
        assertEquals(function.getY(2), 2, ACCURACY);


    }

    @Test
    public void testIndexOfX() {
        assertEquals(listFunction().indexOfX(3), 2, DELTA);
        assertEquals(getListFunction().indexOfX(3), 2, ACCURACY);
        assertEquals(getFunction().indexOfX(0), -1);
        assertEquals(getFunction().indexOfX(5), 6);
        assertEquals(getFunction().indexOfX(-5), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(listFunction().indexOfY(25), 4, DELTA);
        assertEquals(getListFunction().indexOfY(25), 4, ACCURACY);
        assertEquals(getFunction().indexOfY(1), 0);
        assertEquals(getFunction().indexOfY(2), -1);
        assertEquals(getFunction().indexOfY(-2), -1);
    }

    @Test
    public void testLeftBound() {
        assertEquals(listFunction().leftBound(), 1, DELTA);
        assertEquals(getListFunction().leftBound(), 1, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 7, 5);
        assertEquals(testFunction.leftBound(), Double.NaN, ACCURACY);
        assertEquals(getFunction().leftBound(), 1, ACCURACY);
    }

    @Test
    public void testRightBound() {
        assertEquals(listFunction().rightBound(), 5, DELTA);
        assertEquals(getListFunction().rightBound(), 5, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 7, 5);
        assertEquals(testFunction.rightBound(), Double.NaN);
        assertEquals(getFunction().rightBound(), 5, ACCURACY);
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(listFunction().floorIndexOfX(2), 0, DELTA);
        assertEquals(listFunction().floorIndexOfX(11), 10, DELTA);
        assertEquals(listFunction().floorIndexOfX(-10), 0, DELTA);
        assertEquals(getListFunction().floorIndexOfX(2), 0, ACCURACY);
        assertEquals(getListFunction().floorIndexOfX(11), 10, ACCURACY);
        assertEquals(getListFunction().floorIndexOfX(-10), 0, ACCURACY);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 1, 10, 10);
        assertEquals(testFunction.floorIndexOfX(1.8), 0);
        assertEquals(testFunction.floorIndexOfX(-1), 0);
        assertEquals(testFunction.floorIndexOfX(6), 5);
        assertEquals(testFunction.floorIndexOfX(4.5), 3);
        assertEquals(getFunction().floorIndexOfX(2), 1);
        assertEquals(getFunction().floorIndexOfX(-2), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(listFunction().extrapolateLeft(0), -2, DELTA);
        assertEquals(getListFunction().extrapolateLeft(0), -2, ACCURACY);

    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(listFunction().extrapolateRight(4), 16, DELTA);
        assertEquals(getListFunction().extrapolateRight(40), 340, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        assertEquals(listFunction().interpolate(2, listFunction().floorIndexOfX(2)), 4, DELTA);
        assertEquals(listFunction().interpolate(2, listFunction().floorIndexOfX(2)), 4, DELTA);
        assertEquals(listFunction().interpolate(4, listFunction().floorIndexOfX(4)), 16, DELTA);
        assertEquals(getListFunction().interpolate(2, getListFunction().floorIndexOfX(2)), 4, ACCURACY);
        assertEquals(getListFunction().interpolate(2, getListFunction().floorIndexOfX(2)), 4, ACCURACY);
        assertEquals(getListFunction().interpolate(4, getListFunction().floorIndexOfX(4)), 16, ACCURACY);
        assertThrows(InterpolationException.class, () -> listFunction().interpolate(2, 2));
        assertThrows(InterpolationException.class, () -> getListFunction().interpolate(4, 4));
    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction testingApply = new LinkedListTabulatedFunction(xValues, yValues);
        final double delta = 0.0001;
        assertEquals(testingApply.apply(0.9), 1.9, delta);
        assertEquals(testingApply.apply(1.56), 2.56, delta);
        assertEquals(testingApply.apply(1.22), 2.22, delta);
        assertNotEquals(testingApply.apply(1.22), 1.23, delta);
        assertEquals(listFunction().apply(-3.2), -11.6000, delta);
        assertEquals(listFunction().apply(21), 169.0, delta);
        assertEquals(listFunction().apply(7.25), 45.25, delta);
        assertNotEquals(listFunction().apply(7.25), 59.25, delta);
    }

    }

