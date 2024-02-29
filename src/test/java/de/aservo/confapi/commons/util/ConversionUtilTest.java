package de.aservo.confapi.commons.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConversionUtilTest {

    @Test
    public void testConvertLong() {
        assertEquals(0, ConversionUtil.toLong(null));
        assertEquals(0, ConversionUtil.toLong(""));
        assertEquals(100, ConversionUtil.toLong("100"));
        assertEquals(234234234234234L, ConversionUtil.toLong("234234234234234"));
    }

    @Test
    public void testConvertInt() {
        assertEquals(0, ConversionUtil.toInt(null));
        assertEquals(0, ConversionUtil.toInt(""));
        assertEquals(100, ConversionUtil.toInt("100"));
    }

    @Test
    public void testConvertDouble() {
        assertEquals(0, ConversionUtil.toDouble(null), 0);
        assertEquals(0, ConversionUtil.toDouble(""), 0);
        assertEquals(100, ConversionUtil.toDouble("100"), 0);
        assertEquals(100.1231234, ConversionUtil.toDouble("100.1231234"), 0);
    }

    @Test
    public void testConvertBoolean() {
        assertFalse(ConversionUtil.toBoolean(null));
        assertFalse(ConversionUtil.toBoolean(""));
        assertFalse(ConversionUtil.toBoolean("false"));
        assertFalse(ConversionUtil.toBoolean("False"));
        assertFalse(ConversionUtil.toBoolean("no"));
        assertFalse(ConversionUtil.toBoolean("0"));
        assertTrue(ConversionUtil.toBoolean("1"));
        assertTrue(ConversionUtil.toBoolean("true"));
        assertTrue(ConversionUtil.toBoolean("TRUE"));
        assertTrue(ConversionUtil.toBoolean("yes"));
        assertTrue(ConversionUtil.toBoolean("ja"));
    }
}
