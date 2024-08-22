package com.example.lesson10.task2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Triangle implements Shape {
    private double sideLength; // равносторонний
    private String fillColor;
    private String borderColor;

    @Override
    public double calculatePerimeter() {
        return 3 * sideLength;
    }

    @Override
    public double calculateArea() {
        return (Math.sqrt(3) / 4) * sideLength * sideLength;
    }
}