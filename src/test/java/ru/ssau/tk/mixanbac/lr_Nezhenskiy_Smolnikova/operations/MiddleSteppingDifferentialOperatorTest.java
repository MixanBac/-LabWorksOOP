package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.SqrFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class MiddleSteppingDifferentialOperatorTest {

    private static final double STEP = 0.01;

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new MiddleSteppingDifferentialOperator(STEP);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 5.9999, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(6), 11.9999, DELTA);
    }

}