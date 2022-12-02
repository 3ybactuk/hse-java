package ru.hse.javaprogramming;


public class SongPerformanceShow {
    public static void main(String[] args) {
        RadioheadJudge judgeA = new RadioheadJudge();
        HappyJudge judgeB = new HappyJudge();
        HseJudge judgeC = new HseJudge();
        ConfusingJudge judgeD = new ConfusingJudge();

        Judge[] judges = {new RadioheadJudge(), new HappyJudge(), new HseJudge(), new ConfusingJudge()};

        Performance[] performances = {
                new Performance("Яна", "Видеоигры"),
                new Performance("Thom Yorke", "Radiohead - Creep"),
                new Performance("50 Cent", "Hate It Or Love It"),
                new Performance("Johnny Cash", "Hurt")
        };

        for (Performance performance : performances) {
            System.out.println(performance.getCandidateName() + " исполняет '" + performance.getSong() + "'");

            for (Judge judge : judges) {
                Judgement judgement = judge.judge(performance);
                System.out.println(judge.getName() + " говорит: '" + judgement.comment()
                        + "', оценка: " + judgement.points() + " из " + Judgement.MAX_POINTS);
            }
            System.out.println();
        }
    }
}