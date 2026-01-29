package com.sanju;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PasswordValidatorTest {

    private PasswordValidator validator;

    @Before
    public void setUp() {
        validator = new PasswordValidator();
    }

    @Test
    public void testValidPassword() {
        assertTrue(validator.isValid("Password123"));
    }

    @Test
    public void testPasswordTooShort() {
        assertFalse(validator.isValid("Pass1"));
    }

    @Test
    public void testPasswordNoUppercase() {
        assertFalse(validator.isValid("password123"));
    }

    @Test
    public void testPasswordNoDigit() {
        assertFalse(validator.isValid("Password"));
    }

    @Test
    public void testPasswordNull() {
        assertFalse(validator.isValid(null));
    }
}
