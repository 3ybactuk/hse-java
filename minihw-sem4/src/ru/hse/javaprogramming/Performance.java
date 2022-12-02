package ru.hse.javaprogramming;


/**
 * Выступление участника шоу
 */
public class Performance {
    /**
     * Имя кандидата
     */
    private final String candidateName;
    /**
     * Название исполненной песни
     */
    private final String song;

    public Performance(String candidateName, String song) {
        this.candidateName = candidateName;
        this.song = song;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public String getSong() {
        return song;
    }

    @Override
    public String toString() {
        return "Performance{" +
                "candidateName='" + candidateName + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}