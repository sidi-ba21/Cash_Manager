package com.cashmanager.bank.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UStringTest {

    @Test
    void testRandomNumber() {
        int length = 5;

        String result = UString.randomNumber(length);

        assertNotNull(result);
        assertEquals(length, result.length());

        for (char digit : result.toCharArray()) {
            assertTrue(Character.isDigit(digit));
        }
    }

    @Test
    void testRandomNumberWithInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> UString.randomNumber(0));
        assertThrows(IllegalArgumentException.class, () -> UString.randomNumber(-5));
    }

    @Test
    void testRandomNumberWithDifferentLength() {
        int length1 = 8;
        int length2 = 12;

        String result1 = UString.randomNumber(length1);
        String result2 = UString.randomNumber(length2);

        assertEquals(length1, result1.length());
        assertEquals(length2, result2.length());
    }

    @Test
    void testRandomNumberWithLargeLength() {
        int length = 1000;

        String result = UString.randomNumber(length);

        assertEquals(length, result.length());
    }

}
