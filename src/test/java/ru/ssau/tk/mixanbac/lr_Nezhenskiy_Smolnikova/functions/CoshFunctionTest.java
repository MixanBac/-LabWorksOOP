package ru.ssau.tk.mixanbac.lr_Nezhenskiy_Smolnikova.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CoshFunctionTest {

    private final static double DELTA = 0.00001;

    @Test
    public void testApply() {
        CoshFunction testFunction = new CoshFunction();
        assertEquals(testFunction.apply(0),1.0,DELTA);
        assertNotEquals(testFunction.apply(1),10.0, DELTA);
    }

}
