package com.studio.storycollection;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainActivityUnitTest {
    @Test
    public void unit1() {
        // Unit test 1 for MainActivity
        assertEquals(2 + 2, 4);
    }

    @Test
    public void unit2() {
        // Unit test 2 for MainActivity
        assertEquals("Hello", "Hello");
    }

    @Test
    public void unit3() {
        // Unit test for strings.xml
        String[] priče = {"Cinderella", "Snow White", "Little Red Riding Hood"};
        assertEquals(3, priče.length);
    }
}
