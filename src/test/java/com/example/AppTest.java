package com.example;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void testPalindromeNumber() {
        assertTrue(App.isPalindrome(1221));
    }

    @Test
    public void testNonPalindromeNumber() {
        assertFalse(App.isPalindrome(1234));
    }
}
