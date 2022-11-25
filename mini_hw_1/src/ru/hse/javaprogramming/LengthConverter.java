package ru.hse.javaprogramming;

import java.util.Locale;
import java.util.Scanner;

public class LengthConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);  // Для ввода через точку
        System.out.print("Введите длину для перевода (дробь через точку): ");
        double length = scanner.nextDouble();

        System.out.println(length + " см = " + convertToInches(length) + " дюймов");
        System.out.println(length + " дюймов = " + convertToCm(length) + " см");
    }

    public static double convertToInches(double length) { return length / 2.54; }
    public static double convertToCm(double length) { return length * 2.54; }
}