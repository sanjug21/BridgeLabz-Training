package com.sanju;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {

    private UserRegistration userRegistration;

    @Before
    public void setUp() {
        userRegistration = new UserRegistration();
    }

    @Test
    public void testValidRegistration() {
        assertTrue(userRegistration.registerUser("john_doe", "john@example.com", "pass123"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUsername() {
        userRegistration.registerUser(null, "john@example.com", "pass123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUsername() {
        userRegistration.registerUser("", "john@example.com", "pass123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortUsername() {
        userRegistration.registerUser("jo", "john@example.com", "pass123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEmail() {
        userRegistration.registerUser("john_doe", null, "pass123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmailFormat() {
        userRegistration.registerUser("john_doe", "invalid-email", "pass123");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullPassword() {
        userRegistration.registerUser("john_doe", "john@example.com", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testShortPassword() {
        userRegistration.registerUser("john_doe", "john@example.com", "pass");
    }
}
