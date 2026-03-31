package com.sanju;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseConnectionTest {

    private DatabaseConnection databaseConnection;

    @Before
    public void setUp() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
    }

    @After
    public void tearDown() {
        databaseConnection.disconnect();
    }

    @Test
    public void testConnectionEstablished() {
        assertTrue(databaseConnection.isConnected());
    }

    @Test
    public void testConnectionClosed() {
        databaseConnection.disconnect();
        assertFalse(databaseConnection.isConnected());
    }
}
