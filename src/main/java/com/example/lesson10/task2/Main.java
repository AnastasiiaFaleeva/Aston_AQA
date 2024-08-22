package com.example.lesson10.task2;

public class Main {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0f, "Red", "Black");
        Rectangle rectangle = new Rectangle(4.0f, 3.0f, "Blue", "Green");
        Triangle triangle = new Triangle(3.0f, "Yellow", "Purple");

        printShapeInfo(circle, "Круг");
        printShapeInfo(rectangle, "Прямоугольник");
        printShapeInfo(triangle, "Треугольник");
    }

    private static void printShapeInfo(Shape shape, String shapeName) {
        System.out.println("Фигура: " + shapeName);
        System.out.println("Периметр: " + shape.calculatePerimeter());
        System.out.println("Площадь: " + shape.calculateArea());
        System.out.println("Цвет заливки: " + shape.getFillColor());
        System.out.println("Цвет границы: " + shape.getBorderColor());
        System.out.println();
    }
}