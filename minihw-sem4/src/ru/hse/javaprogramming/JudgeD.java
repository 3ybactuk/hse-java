package ru.hse.javaprogramming;

public class JudgeC extends Judge {
    @Override
    public Judgement judge(Performance performance) {
        return new Judgement((int) (Judgement.MAX_POINTS * 0.8), "Very cool, but grade inflation, sorry.");
    }

    @Override
    public String getName() {
        return "HSE head office's judge";
    }
}
