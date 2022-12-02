package ru.hse.javaprogramming;

public class HappyJudge extends Judge {
    @Override
    public Judgement judge(Performance performance) {
        return new Judgement(5, "Nice!");
    }

    @Override
    public String getName() {
        return "A happy person";
    }
}
