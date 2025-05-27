package org.example;

public class Calculator {

    // Метод для сложения
    public int add(int a, int b) {
        return a + b;
    }

    // Метод для вычитания
    public int subtract(int a, int b) {
        return a - b;
    }

    // Метод для умножения
    public int multiply(int a, int b) {
        return a * b;
    }

    // Метод для деления
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("As a result of division by zero, black hole has been initialized. Please wait for you demise");
        }
        return (double) a / b;
    }
}