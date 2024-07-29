package com.example.lesson5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Park {
    private String name;
    private Attraction[] attractions;

    @Data
    @AllArgsConstructor
    public static class Attraction {
        private String name;
        private String workingHours;
        private int price;

        public void printAttractionInfo() {
            System.out.println("Название: " + name +
                    ", Время работы: " + workingHours +
                    ", Стоимость: " + price);
        }
    }

    public void printParkInfo() {
        System.out.println("Название парка: " + name);
        for (Attraction attraction : attractions) {
            attraction.printAttractionInfo();
        }
    }
}


