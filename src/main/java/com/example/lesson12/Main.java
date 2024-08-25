package com.example.lesson12;

public class Main {
    public static void main(String[] args) {
        String[][][] arrays = {
                {
                        {"1", "1", "1", "1"},
                        {"2", "2", "2", "2"},
                        {"3", "3", "3", "3"},
                        {"4", "4", "4", "4"}
                },
                {
                        {"1", "1", "1"},
                        {"2", "2", "2"},
                        {"3", "3", "3"}
                },
                {
                        {"1", "1", "1", "1"},
                        {"2", "2", "2", "2"},
                        {"3", "3", "A", "3"},
                        {"4", "4", "4", "4"}
                }
        };

        for (String[][] array : arrays) {
            MyArrayMethods myArray = new MyArrayMethods(array);
            try {
                myArray.printArray();
                int result = myArray.processAndSumArrayElements();
                System.out.println("Сумма элементов массива: " + result);
            } catch (MyArraySizeException | MyArrayDataException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
            System.out.println();
        }
    }
}