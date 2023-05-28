package ru.hse.javaprogramming;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains the set of words, and the methods to manipulate it.
 */
public class Words {
    /**
     * The set of words.
     */
    private static final Set<String> words = new HashSet<>();

    /**
     * Adds the input word to set of words.
     * Shouldn't contain whitespaces.
     * @param word word to be added
     */
    public static void addWord(String word) {
        if (word.contains(" ")) {
            System.out.println("Ошибка: в строке есть пробелы.");
        } else if (words.add(word.toLowerCase())) {
            System.out.println("Ок. Слово \"" + word + "\" добавлено.");
        } else {
            System.out.println("Слово \"" + word + "\" Вы уже вводили.");
        }
    }
}
