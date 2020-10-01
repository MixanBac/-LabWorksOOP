package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double delta = 0.0005;
    private final MathFunction sqr = new SqrFunction();
    private final MathFunction identityFunction = new IdentityFunction();
    private final ArrayTabulatedFunction function = new ArrayTabulatedFunction(sqr, -2, 2, 5);

    @Test
    public void testGetCount() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -1, 1, 1);
        assertNotEquals(function.getCount(), 0);
        assertNotEquals(testFunction.getCount(), 0);
        assertEquals(function.getCount(), 5);
        assertEquals(testFunction.getCount(), 1);
    }

    @Test
    public void testGetX() {
        assertNotEquals(function.getX(0), Double.NaN);
        assertNotEquals(function.getX(4), Double.NaN);
        assertEquals(function.getX(1), -1, delta);
        assertEquals(function.getX(4), 2, delta);
    }

    @Test
    public void testGetY() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, Double.NaN, 3, 5);
        assertEquals(testFunction.getY(1), Double.NaN);
        assertEquals(testFunction.getY(3), Double.NaN);
        assertEquals(testFunction.getY(4), 3, delta);
        assertNotEquals(function.getY(4), Double.NaN);
        assertNotEquals(function.getY(0), Double.NaN);
        assertEquals(function.getY(2), 0, delta);
        assertEquals(function.getY(3), 1, delta);
    }

    @Test
    public void testSetY() {
        function.setY(4, 5);
        function.setY(2, 111);
        function.setY(3, Double.NaN);
        assertEquals(function.getY(4), 5, delta);
        assertEquals(function.getY(2), 111, delta);
        assertEquals(function.getY(3), Double.NaN);
        function.setY(3, 1);
        function.setY(2, 0);
        function.setY(4, 4);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(function.indexOfX(2.00001), 4);
        assertEquals(function.indexOfX(0.0004), 2);
        assertNotEquals(function.indexOfX(1000), Double.NaN);
        assertNotEquals(function.indexOfX(-1000), Double.NaN);
        assertEquals(function.indexOfX(Double.NaN), -1);
        assertEquals(function.indexOfX(5), -1);
        assertEquals(function.indexOfX(-5), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(function.indexOfY(1.00001), 1);
        assertEquals(function.indexOfY(4.0002), 0);
        assertNotEquals(function.indexOfY(1000), Double.NaN);
        assertNotEquals(function.indexOfY(-1000), Double.NaN);
        assertEquals(function.indexOfY(Double.NaN), -1);
        assertEquals(function.indexOfY(5), -1);
        assertEquals(function.indexOfY(-5), -1);
    }

    @Test
    public void testLeftBound() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 1);
        assertEquals(testFunction.leftBound(), Double.NaN, delta);
        assertEquals(anotherFunction.leftBound(), 1, delta);
        assertEquals(function.leftBound(), -2, delta);
    }

    @Test
    public void testRightBound() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, Double.NaN, 5, 1);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, 1, Double.NaN, 1);
        assertEquals(testFunction.rightBound(), Double.NaN);
        assertEquals(anotherFunction.rightBound(), 1, delta);
        assertEquals(function.rightBound(), 2, delta);
    }

    @Test
    public void testFloorIndexOfX() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, 1, 5, 10);
        assertEquals(testFunction.floorIndexOfX(1.8), 1);
        assertEquals(testFunction.floorIndexOfX(-1), 0);
        assertEquals(testFunction.floorIndexOfX(6), 10);
        assertEquals(testFunction.floorIndexOfX(4.5), 5.2);
        assertEquals(function.floorIndexOfX(2), 3);
        assertEquals(function.floorIndexOfX(-2), 0);
    }

    @Test
    public void testFloorIndexOfXNaN() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(identityFunction, Double.NaN, 5, 3);
        final ArrayTabulatedFunction someFunction = new ArrayTabulatedFunction(identityFunction, -3, Double.NaN, 5);
        assertEquals(anotherFunction.floorIndexOfX(3), -1);
        assertEquals(someFunction.floorIndexOfX(-4), 0);
        assertEquals(someFunction.floorIndexOfX(-3), 0);
    }

    @Test
    public void testExtrapolateLeft() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        assertEquals(function.extrapolateLeft(-3), 7, delta);
        assertEquals(testFunction.extrapolateLeft(-10), -10, delta);
        assertEquals(anotherFunction.extrapolateLeft(-10), 71.6667, delta);
    }

    @Test
    public void testExtrapolateRight() {
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(identityFunction, -5, 5, 20);
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -7, 3, 10);
        assertEquals(function.extrapolateRight(4), 10, delta);
        assertEquals(testFunction.extrapolateRight(10), 10, delta);
        assertEquals(anotherFunction.extrapolateRight(7), 28.5556, delta);
    }

    @Test
    public void testInterpolate() {
        final ArrayTabulatedFunction anotherFunction = new ArrayTabulatedFunction(sqr, -7, 3, 10);
        final ArrayTabulatedFunction testFunction = new ArrayTabulatedFunction(sqr, -5, 1, 10);
        assertEquals(anotherFunction.interpolate(7, anotherFunction.floorIndexOfX(7)), 28.5556, delta);
        assertEquals(testFunction.interpolate(-10, testFunction.floorIndexOfX(-10)), 71.6667, delta);
        assertEquals(function.interpolate(-0.5, function.floorIndexOfX(-0.5)), 0.5, delta);
        assertEquals(testFunction.interpolate(-2.5, testFunction.floorIndexOfX(-2.5)), 6.3335, delta);
    }
}