package ru.hse.javaprogramming;

import java.util.Objects;

/**
 * Заключение судьи
 *
 * @param points  Количество баллов, которое поставил судья
 * @param comment Комментарий судьи
 */
public record Judgement(
        int points,
        String comment
) {
    /**
     * Максимум баллов
     */
    public static final int MAX_POINTS = 5;
    /**
     * Минимум баллов
     */
    public static final int MIN_POINTS = 1;

    // Это - компактный конструктор.
    // Такой конструктор не имеет формальных параметров и предназначен для валидации компонент record'а: points и comment
    // в этом классе.
    // Но никто не мешает написать в его теле написать всё, что угодно.
    // Компактный конструктор доступен только для record'ов.
    // Подробнее - по ссылке: https://docs.oracle.com/en/java/javase/17/language/records.html
    public Judgement {
        if (points < MIN_POINTS || points > MAX_POINTS) {
            throw new IllegalArgumentException("points must be in range [" + MIN_POINTS + ", " + MAX_POINTS + "]");
        }
        Objects.requireNonNull(comment);
    }
}