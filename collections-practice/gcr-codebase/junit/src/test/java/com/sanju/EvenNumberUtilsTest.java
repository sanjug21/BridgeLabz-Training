package com.sanju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EvenNumberUtilsTest {

    private final int input;
    private final boolean expected;

    public EvenNumberUtilsTest(int input, boolean expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: isEven({0})={1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {2, true},
                {4, true},
                {6, true},
                {7, false},
                {9, false}
        });
    }

    @Test
    public void testIsEven() {
        EvenNumberUtils utils = new EvenNumberUtils();
        assertEquals(expected, utils.isEven(input));
    }
}
