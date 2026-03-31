package com.sanju;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {

	private StringUtils stringUtils;

	@Before
	public void setUp() {
		stringUtils = new StringUtils();
	}

	// Test cases for reverse method
	@Test
	public void testReverseSimpleString() {
		String result = stringUtils.reverse("hello");
		assertEquals("olleh", result);
	}

	@Test
	public void testReverseWithSpaces() {
		String result = stringUtils.reverse("hello world");
		assertEquals("dlrow olleh", result);
	}

	@Test
	public void testReverseNullString() {
		String result = stringUtils.reverse(null);
		assertNull(result);
	}

	// Test cases for isPalindrome method
	@Test
	public void testIsPalindromeSimple() {
		boolean result = stringUtils.isPalindrome("racecar");
		assertTrue(result);
	}

	@Test
	public void testIsPalindromeWithSpaces() {
		boolean result = stringUtils.isPalindrome("A man a plan a canal Panama");
		assertTrue(result);
	}

	@Test
	public void testIsNotPalindrome() {
		boolean result = stringUtils.isPalindrome("hello");
		assertFalse(result);
	}

	// Test cases for toUpperCase method
	@Test
	public void testToUpperCaseLowercase() {
		String result = stringUtils.toUpperCase("hello");
		assertEquals("HELLO", result);
	}

	@Test
	public void testToUpperCaseMixedCase() {
		String result = stringUtils.toUpperCase("HeLLo WoRLd");
		assertEquals("HELLO WORLD", result);
	}

	@Test
	public void testToUpperCaseNullString() {
		String result = stringUtils.toUpperCase(null);
		assertNull(result);
	}
}
