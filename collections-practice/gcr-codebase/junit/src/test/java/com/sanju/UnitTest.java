package com.sanju;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class UnitTest {

    @Test
    public void Test_Deposit_ValidAmount() {
        Program account = new Program(100.0);

        account.deposit(50.0);

        assertEquals(150.0, account.getBalance(), 0.001);
    }

    @Test
    public void Test_Deposit_NegativeAmount() {
        Program account = new Program(100.0);
        String message = null;

        try {
            account.deposit(-10.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }

        assertEquals("Deposit amount cannot be negative", message);
    }

    @Test
    public void Test_Withdraw_ValidAmount() {
        Program account = new Program(100.0);

        account.withdraw(40.0);

        assertEquals(60.0, account.getBalance(), 0.001);
    }

    @Test
    public void Test_Withdraw_InsufficientFunds() {
        Program account = new Program(100.0);
        String message = null;

        try {
            account.withdraw(150.0);
        } catch (IllegalArgumentException ex) {
            message = ex.getMessage();
        }

        assertEquals("Insufficient funds.", message);
    }
}
