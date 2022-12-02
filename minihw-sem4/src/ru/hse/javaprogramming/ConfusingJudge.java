package ru.hse.javaprogramming;

public class ConfusingJudge extends Judge {
    @Override
    public Judgement judge(Performance performance) {
        /// 2 * ПР * Т / (ПР + Т)
        double criteria1 = 3, criteria2 = 3;
        if (performance.getSong().length() > 10) {
            criteria1 = 5;
        }
        if (performance.getCandidateName().contains("John")) {
            criteria2 = 1;
        }

        double total = 2 * criteria1 * criteria2 / (criteria1 + criteria2);

        return new Judgement((int) total, "Skill issue.");
    }

    @Override
    public String getName() {
        return "Insane grading formulae enjoyer";
    }
}
