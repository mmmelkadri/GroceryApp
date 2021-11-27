package com.example.groceryapp;

import org.junit.Test;

import static org.junit.Assert.*;

// Mohamad El Kadri Testing Suite
public class ReadingUnitTest {
    @Test
    public void readValueTest1() {
        String actual = Reader.getInstance().readValue("test", "Catherine", "username");

        assertEquals(actual, "UCath");
    }

    @Test
    public void readValueTest2() {
        assertEquals(4, 2 + 2);
    }
}