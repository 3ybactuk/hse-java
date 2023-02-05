package ru.hse.javaprogramming;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SeekNumberSeekerTests {
    @Test
    void zeroZeroes() {
        SeekNumberMain.setZeroes(0);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 != 0);
    }

    @Test
    void oneZero() {
        SeekNumberMain.setZeroes(1);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 != 0);
    }

    @Test
    void twoZeroes() {
        SeekNumberMain.setZeroes(2);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000 != 0);
    }

    @Test
    void threeZeroes() {
        SeekNumberMain.setZeroes(3);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 10000 != 0);
    }

    @Test
    void fourZeroes() {
        SeekNumberMain.setZeroes(4);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 10000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100000 != 0);
    }

    @Test
    void fiveZeroes() {
        SeekNumberMain.setZeroes(5);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 10000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000000 != 0);
    }

    @Test
    void sixZeroes() {
        SeekNumberMain.setZeroes(6);
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker();

        System.out.println(SeekNumberMain.getZeroes());
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.foundNumber % 10 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 10000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 100000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 1000000 == 0);
        assertTrue(seekNumberSeeker.foundNumber % 10000000 != 0);
    }
}
