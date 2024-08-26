package com.example.lesson13.task2;

import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "123456");
        phoneBook.add("Петров", "234567");
        phoneBook.add("Иванов", "345678");
        phoneBook.add("Сидоров", "456789");
        phoneBook.add("Иванов", "567890");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Добавить имя и телефон, введите 1.");
                System.out.println("Найти телефон по фамилии, введите 2.");
                System.out.println("Выход из программы введите 0.");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.println();
                    System.out.print("Введите фамилию: ");
                    String surname = scanner.nextLine();
                    System.out.print("Сколько номеров телефонов вы хотите добавть к контакту? ");
                    int numberOfPhones = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < numberOfPhones; i++) {
                        System.out.print("Введите номер телефона #" + (i + 1) + ": ");
                        String phoneNumber = scanner.nextLine();
                        phoneBook.add(surname, phoneNumber);
                        System.out.println("Номер добавлен.");
                    }
                    System.out.println();

                } else if (choice == 2) {
                    System.out.println();
                    System.out.print("Введите фамилию для поиска номеров телефонов: ");
                    String surnameToSearch = scanner.nextLine();
                    List<String> phones = phoneBook.get(surnameToSearch);
                    if (phones.isEmpty()) {
                        System.out.println("Номера телефонов для фамилии " + surnameToSearch +
                                " не найдены.");
                    } else {
                        System.out.println("Номера телефонов для фамилии " + surnameToSearch +
                                ": " + phones);
                    }
                    System.out.println();

                } else if (choice == 0) {
                    System.out.println("Выход из программы.");
                    break;
                } else {
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1, 2 или 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите число 1, 2 или 0.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}