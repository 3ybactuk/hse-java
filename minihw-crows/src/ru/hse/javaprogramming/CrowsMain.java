package ru.hse.javaprogramming;

import java.util.Scanner;

/**
 * Main class, contains input output handling.
 */
public class CrowsMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        iohandler();
    }

    /**
     * Handles user input and output to console.
     */
    public static void iohandler() {
        String input;

        System.out.println("Введите слово или \"exit\" для выхода");
        while (true) {
            System.out.print("> ");

            if (!scanner.hasNext()) {
                break;
            }

            input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            } else {
                Words.addWord(input);
            }

            System.out.println("Введите новое слово или \"exit\" для выхода");
        }

        System.out.println("До свидания!");
    }
}
