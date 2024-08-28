package com.example.lesson13.task1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.*;

@Getter
@RequiredArgsConstructor

public class WordArrayProcessor {
    private final String[] words;

    public List<String> getUniqueWords() {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        List<String> uniqueWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueWords.add(entry.getKey());
            }
        }
        return uniqueWords;
    }

    public Map<String, Integer> getWordCount() {
        return countWords(words);
    }

    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.containsKey(word)) {
                wordCount.put(word, wordCount.get(word) + 1);
            } else {
                wordCount.put(word, 1);
            }
        }
        return wordCount;
    }
}