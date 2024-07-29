package com.example.lesson5;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Иванов Иван", "Инженер",
                "ivivan@mailbox.com", "892312312", 30000, 30);
        employees[1] = new Employee("Петров Петр", "Менеджер",
                "petrov@mailbox.com", "892312313", 25000, 35);
        employees[2] = new Employee("Сидоров Сидор", "Директор",
                "sidorov@mailbox.com", "892312314", 50000, 40);
        employees[3] = new Employee("Кузнецов Кузьма", "Тестировщик",
                "kuznetsov@mailbox.com", "892312315", 35000, 25);
        employees[4] = new Employee("Смирнов Дмитрий", "Програмист",
                "smirnov@mailbox.com", "892312316", 40000, 28);

        System.out.println("Информация о сотрудниках:");
        for (Employee employee : employees) {
            System.out.println("Имя: " + employee.getFullName() +
                    ", Должность: " + employee.getPosition() +
                    ", Email: " + employee.getEmail() +
                    ", Телефон: " + employee.getPhone() +
                    ", Зарплата: " + employee.getSalary() +
                    ", Возраст: " + employee.getAge());
        }

        Park.Attraction[] attractions = new Park.Attraction[3];
        attractions[0] = new Park.Attraction("Ролердром", "10:00 - 20:00",
                1000);
        attractions[1] = new Park.Attraction("Батуты", "09:00 - 18:00",
                500);
        attractions[2] = new Park.Attraction("Колесо обозрения", "11:00 - 22:00",
                800);

        System.out.println("Информация об аттракционах:");
        for (Park.Attraction attraction : attractions) {
            System.out.println("Название: " + attraction.getName() +
                    ", Время работы: " + attraction.getWorkingHours() +
                    ", Стоимость: " + attraction.getPrice());
        }
    }
}
