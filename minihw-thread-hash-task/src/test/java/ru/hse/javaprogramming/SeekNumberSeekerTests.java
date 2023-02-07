package ru.hse.javaprogramming;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SeekNumberSeekerTests {
    @Test
    void zeroZeroes() {
        int zeroes = 0;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 != 0);
    }

    @Test
    void oneZero() {
        int zeroes = 1;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 != 0);
    }

    @Test
    void twoZeroes() {
        int zeroes = 2;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000 != 0);
    }

    @Test
    void threeZeroes() {
        int zeroes = 3;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 10000 != 0);
    }

    @Test
    void fourZeroes() {
        int zeroes = 4;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 10000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100000 != 0);
    }

    @Test
    void fiveZeroes() {
        int zeroes = 5;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 10000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000000 != 0);
    }

    @Test
    void sixZeroes() {
        int zeroes = 6;
        SeekNumberSeeker seekNumberSeeker = new SeekNumberSeeker(zeroes);

        System.out.println(zeroes);
        seekNumberSeeker.run();

        assertTrue(seekNumberSeeker.getFoundNumber() % 10 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 10000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 100000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 1000000 == 0);
        assertTrue(seekNumberSeeker.getFoundNumber() % 10000000 != 0);
    }
}
