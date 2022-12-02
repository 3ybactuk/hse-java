package ru.hse.javaprogramming;


/**
 * Судья в шоу
 */
public abstract class Judge {
    /**
     * Оценить выступление участника.
     *
     * @param performance выступление для оценки
     * @return результат оценивания
     */
    public abstract Judgement judge(Performance performance);

    /**
     * @return имя судьи
     */
    public abstract String getName();
}


