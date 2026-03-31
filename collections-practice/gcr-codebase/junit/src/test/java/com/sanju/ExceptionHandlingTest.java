package com.sanju;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExceptionHandlingTest {

    private ExceptionHandling exceptionHandling;

    @Before
    public void setUp() {
        exceptionHandling = new ExceptionHandling();
    }
    

    @Test(expected = ArithmeticException.class)
    public void testDivideByZeroThrowsException() {
        exceptionHandling.divide(10, 0);
    }

    @Test
    public void testDivideValidNumbers() {
        int result = exceptionHandling.divide(10, 2);
        assertEquals(5, result);
    }
}
