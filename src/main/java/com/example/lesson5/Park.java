package com.example.lesson5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Park {
    private Attraction attraction;

    @Data
    @AllArgsConstructor
    public static class Attraction {
        private String name;
        private String workingHours;
        private int price;

        public void printAttractionInfo() {
            System.out.println(this);
        }
    }
}
