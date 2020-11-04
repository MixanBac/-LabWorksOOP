package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions.SqrFunction;

import static org.testng.Assert.*;
import static ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.operations.TabulatedDifferentialOperatorTest.DELTA;

public class MiddleSteppingDifferentialOperatorTest {

    private static final double STEP = 0.01;

    @Test
    public void testDerive() {
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(STEP);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2.010, DELTA);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(3), 6.0099, DELTA);
    }

}