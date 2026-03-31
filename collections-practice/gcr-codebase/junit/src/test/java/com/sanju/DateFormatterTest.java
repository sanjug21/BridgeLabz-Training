package com.sanju;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;

public class DateFormatterTest {

    private DateFormatter formatter;

    @Before
    public void setUp() {
        formatter = new DateFormatter();
    }

    @Test
    public void testValidDateFormat() throws ParseException {
        assertEquals("15-08-2023", formatter.formatDate("2023-08-15"));
        assertEquals("01-01-2024", formatter.formatDate("2024-01-01"));
        assertEquals("31-12-2025", formatter.formatDate("2025-12-31"));
    }

    @Test(expected = ParseException.class)
    public void testInvalidDateFormat() throws ParseException {
        formatter.formatDate("15-08-2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDate() throws ParseException {
        formatter.formatDate(null);
    }
}
