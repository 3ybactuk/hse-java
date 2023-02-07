package ru.hse.javaprogramming;

import java.util.Random;

public class SeekNumberSeeker implements Runnable {
    private static final Random random = new Random();
    private final int zeroes;
    private int foundNumber;
    private boolean isFound = false;

    public SeekNumberSeeker(int zeroes) {
        this.zeroes = zeroes;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        isFound = false;
        foundNumber = random.nextInt();

        while (!checkZeroes(foundNumber) && (!current.isInterrupted())) {
            foundNumber = random.nextInt();
        }

        if (!current.isInterrupted()) {
            isFound = true;
            System.out.println();
            System.out.println("Число: " + foundNumber);
        }
    }

    private boolean checkZeroes(int num) {
        return (num % (Math.pow(10, zeroes)) == 0) && ((num % (Math.pow(10, zeroes + 1)) != 0));
    }

    public int getFoundNumber() {
        return foundNumber;
    }

    public boolean isFound() {
        return isFound;
    }
}
