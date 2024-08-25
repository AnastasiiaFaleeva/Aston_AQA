package com.example.lesson12;

public class MyArrayMethods {

    private final String[][] array;

    public MyArrayMethods(String[][] array) {
        this.array = array;
    }

    public void checkArraySize() throws MyArraySizeException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Массив должен быть размером 4x4");
        }
    }

    public int processAndSumArrayElements() throws MyArraySizeException, MyArrayDataException {
        checkArraySize();
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    int num = Integer.parseInt(array[i][j]);
                    sum += num;
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Ошибка в данных массива в ячейке" +
                            " [" + i + "][" + j + "]: " + array[i][j]);
                }
            }
        }
        return sum;
    }

    public void printArray() {
        System.out.println("Массив:");
        for (String[] strings : array) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}