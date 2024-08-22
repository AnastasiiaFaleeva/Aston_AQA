package com.example.lesson10.task1;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class Bowl {
    private int food;

    public boolean decreaseFood(int amount) {
        if (amount <= food) {
            System.out.println("В миске было " + food + " еды.");
            food -= amount;
            System.out.println("Кот съел " + amount + " еды.");
            return true;
        } else {
            return false;
        }
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавили " + amount + " еды.");
    }

    public void feedCat(Cat cat, int amount) {
        if (decreaseFood(amount)) {
            cat.setFull(true);
            System.out.println(cat.getName() + " съел " + amount + " еды и теперь сыт.");
        } else {
            System.out.println(cat.getName() + " не может съесть " + amount + " еды, " +
                    "потому что в миске только " + food + ". Кот остаётся голодным.");
        }
    }
}