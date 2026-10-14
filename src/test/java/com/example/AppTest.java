package com.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppTest {
    @Test
    public void testPalindromeNumberPasses() {
        assertTrue(App.isPalindrome(1221));
    }

    @Test
    public void testNonPalindromeNumberFails() {
        assertTrue(App.isPalindrome(1234));
    }
}
