package ru.hse.javaprogramming;

import java.util.Scanner;

public class SeekNumberMain {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        iohandler();
    }

    private static void iohandler() {
        System.out.println("Введите число нулей справа");
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(0);
        Thread thread = new Thread(seekNumberSeeker);

        while (true) {
            String input = input();

            if (input.equals("exit")) {
                break;
            } else if (!seekNumberSeeker.isFound() && thread.isAlive() && !thread.isInterrupted()) {
                thread.interrupt();
                System.out.println("Ок, поиск прерван. Введите число нулей справа");
            } else {
                try {
                    int zeroes = Integer.parseInt(input);
                    if ((0 > zeroes) || (zeroes > Integer.toString(Integer.MAX_VALUE).length() - 1)) {
                        System.out.println("Введено неверное количество нулей. Число должно быть в диапазоне от 0 до "
                                + (Integer.toString(Integer.MAX_VALUE).length() - 1));
                        continue;
                    }
                    seekNumberSeeker = new SeekNumberSeeker(zeroes);
                } catch (NumberFormatException e) {
                    System.out.println("Введите число нулей справа");
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
        System.out.print("> ");

        if (!scanner.hasNext()) {
            return "exit";
        }

        return scanner.nextLine();
    }
}
