package ru.hse.javaprogramming;

import java.util.Random;

public class SeekNumberSeeker implements Runnable {
    private static final Random random = new Random();
    public int foundNumber;

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        foundNumber = random.nextInt();

        while (!checkZeroes(foundNumber) && (!current.isInterrupted())) {
            foundNumber = random.nextInt();
        }

        if (!current.isInterrupted()) {
            System.out.println();
            System.out.println("Число: " + foundNumber);
        }
    }

    private boolean checkZeroes(int num) {
        return (num % (Math.pow(10, SeekNumberMain.getZeroes())) == 0) && ((num % (Math.pow(10, SeekNumberMain.getZeroes() + 1)) != 0));
    }
}
