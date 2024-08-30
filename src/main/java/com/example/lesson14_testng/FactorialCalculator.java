package com.example.lesson14_testng;

public class FactorialCalculator {

    public long calculateFactorial(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Нельзя вычислить факториал отрицательного числа");
        }
        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}