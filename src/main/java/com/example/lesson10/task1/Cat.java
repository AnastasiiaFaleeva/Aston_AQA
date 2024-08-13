package com.example.lesson10.task1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Data
@EqualsAndHashCode(callSuper = true)
public class Cat extends Animal {
    @Getter
    private static int catCount = 0;
    private boolean isFull = false;

    public Cat(String name) {
        super(name, 200, 0);
        catCount++;
    }

    public void eat(Bowl bowl, int foodAmount) {
        if (bowl.decreaseFood(foodAmount)) {
            System.out.println(getName() + " съел " + foodAmount + " еды.");
            isFull = true;
        } else {
            System.out.println(getName() + " не хватает еды.");
        }
    }
}