package com.example.lesson13.task1;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String[] words = {"мандарин", "конфеты", "мандарин",
                "арбуз", "арбуз", "конфеты", "кофе",
                "чай", "конфеты", "мандарин", "чай", "арбуз", "яблоко"};

        WordArrayProcessor processor = new WordArrayProcessor(words);

        System.out.println("Уникальные слова: " + processor.getUniqueWords());

        System.out.println("Частота встречаемости слов:");
        for (Map.Entry<String, Integer> entry : processor.getWordCount().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}