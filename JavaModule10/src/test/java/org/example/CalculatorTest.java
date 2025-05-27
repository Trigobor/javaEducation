package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calc = new Calculator();
        assertEquals(5, calc.add(2, 3)); // Проверяем, что 2 + 3 = 5
    }

    @Test
    public void testSubtract() {
        Calculator calc = new Calculator();
        assertEquals(1, calc.subtract(3, 2)); // Проверяем, что 3 - 2 = 1
    }

    @Test
    public void testMultiply() {
        Calculator calc = new Calculator();
        assertEquals(6, calc.multiply(3, 2)); // Проверяем, что 3 * 2 = 6
    }

    @Test
    public void testDivide() {
        Calculator calc = new Calculator();
        assertEquals(3, calc.divide(6, 2)); // Проверяем, что 6 / 2 = 3
    }
}