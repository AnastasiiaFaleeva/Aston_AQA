package com.example.lesson10.task1;

public class Main {
    public static void main(String[] args) {
        Dog dogBobik = new Dog("Бобик");
        Cat[] cats = {
                new Cat("Барсик"),
                new Cat("Мурзик"),
                new Cat("Васька")
        };

        dogBobik.run(150);
        dogBobik.swim(5);

        for (Cat cat : cats) {
            cat.run(100);
            cat.swim(5);
        }

        Bowl[] bowls = {
                new Bowl(10),
                new Bowl(10),
                new Bowl(5)
        };

        int[] foodAmounts = {10, 5, 10};
        for (int i = 0; i < cats.length; i++) {
            bowls[i].feedCat(cats[i], foodAmounts[i]);
        }
    }
}