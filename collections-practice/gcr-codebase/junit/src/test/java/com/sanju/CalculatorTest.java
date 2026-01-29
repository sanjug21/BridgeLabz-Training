package com.sanju;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    // Test cases for add method
    @Test
    public void testAddPositiveNumbers() {
        int result = calculator.add(5, 3);
        assertEquals(8, result);
    }

    @Test
    public void testAddNegativeNumbers() {
        int result = calculator.add(-5, -3);
        assertEquals(-8, result);
    }

    @Test
    public void testAddMixedNumbers() {
        int result = calculator.add(-5, 3);
        assertEquals(-2, result);
    }

    // Test cases for subtract method
    @Test
    public void testSubtractPositiveNumbers() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    public void testSubtractResultNegative() {
        int result = calculator.subtract(4, 10);
        assertEquals(-6, result);
    }

    @Test
    public void testSubtractZero() {
        int result = calculator.subtract(5, 0);
        assertEquals(5, result);
    }

    // Test cases for multiply method
    @Test
    public void testMultiplyPositiveNumbers() {
        int result = calculator.multiply(5, 3);
        assertEquals(15, result);
    }

    @Test
    public void testMultiplyMixedNumbers() {
        int result = calculator.multiply(-5, 3);
        assertEquals(-15, result);
    }

    @Test
    public void testMultiplyByZero() {
        int result = calculator.multiply(5, 0);
        assertEquals(0, result);
    }

    // Test cases for divide method
    @Test
    public void testDividePositiveNumbers() {
        int result = calculator.divide(10, 2);
        assertEquals(5, result);
    }

    @Test
    public void testDivideMixedNumbers() {
        int result = calculator.divide(-10, 2);
        assertEquals(-5, result);
    }

    // Bonus: Test for division by zero
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
    }
    
}
