package ru.hse.javaprogramming;

public class JudgeB extends Judge {
    @Override
    public double judge(Performance performance) {
        return 5;
    }

    @Override
    public String getName() {
        return "A happy person";
    }
}
