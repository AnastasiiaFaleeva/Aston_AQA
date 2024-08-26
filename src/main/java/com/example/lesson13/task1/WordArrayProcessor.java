package com.example.lesson13.task1;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;

@Getter
@RequiredArgsConstructor
public class WordArrayProcessor {
    private final String[] words;

    public Set<String> getUniqueWords() {
        return new HashSet<>(Arrays.asList(words));
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