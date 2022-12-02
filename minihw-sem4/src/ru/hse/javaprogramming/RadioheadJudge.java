package ru.hse.javaprogramming;

public class RadioheadJudge extends Judge {
    @Override
    public Judgement judge(Performance performance) {
        if (performance.getSong().contains("Radiohead")) {
            return new Judgement(5, "I love Radiohead!");
        }
        return new Judgement(3, "No Radiohead, baaaaad.");
    }

    @Override
    public String getName() {
        return "Average Radiohead enjoyer";
    }
}

