package ru.hse.javaprogramming;

import java.util.Scanner;

public class SeekNumberMain {
    private static final Scanner scanner = new Scanner(System.in);
    private static int zeroes;
    public static void main(String[] args) {
        iohandler();
    }

    private static void iohandler() {
        System.out.println("Введите число нулей справа");
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();
        Thread thread = new Thread(seekNumberSeeker);

        while (true) {
            String input = input();

            if (input.equals("exit")) {
                break;
            } else if (thread.isAlive() && !thread.isInterrupted()) {
                thread.interrupt();
                System.out.println("Ок, поиск прерван. Введите число нулей справа");
            } else {
                try {
                    zeroes = Integer.parseInt(input);
                    if (zeroes < 0) {
                        zeroes = 0;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }

                System.out.println("Ок, запускаю поток. Введите любой символ, чтобы прервать поиск");
                thread = new Thread(seekNumberSeeker);
                thread.start();
            }
        }

        System.out.println("До свидания!");
    }

    private static String input() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("> ");

        if (!scanner.hasNext()) {
            return "exit";
        }

        return scanner.nextLine();
    }

    public static int getZeroes() {
        return zeroes;
    }

    public static void setZeroes(int zeroes) {
        SeekNumberMain.zeroes = zeroes;
    }
}
