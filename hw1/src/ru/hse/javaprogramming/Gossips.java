package ru.hse.javaprogramming;

import java.util.Scanner;

public class Gossips {
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
        Scanner scanner = new Scanner(System.in);

    }

    public static void create(String name, String type) {

    }

    public static void link(String name1, String name2) {

    }

    public static void unlink(String name1, String name2) {

    }

    public static void message(String name, String message) {

    }

    public static void gossips() {

    }

    public static void listeners(String name) {

    }

    public static void quit() {

    }

    public static void about() {
        System.out.println("""
                Работу выполнил:
                Студент группы БПИ211-2
                Шубин Никита Васильевич.
                """);
    }

    public static void help() {
        System.out.println("""
                сreate      <name>  <type>
                                        Создать экземпляр сплетницы с указанным
                                        именем.
                                        Возможные значения type:
                                        1. null
                                        2. censor
                                        3. spammer
                                        4. simple
                                        5. deduplicator
                                        Максимальное количество сплетниц – 100. При
                                        превышении максимального числа сплетниц
                                        приложение печатает сообщение об ошибке и
                                        ожидает ввод новой команды
                link        <name1> <name2>
                                        Зарегистрировать сплетницу с именем
                                        <name2> слушателем сообщений от сплетницы
                                        с именем <name1>
                unlink      <name1> <name2>
                                        Убрать сплетницу с именем <name2> из
                                        слушателей сообщений от сплетницы с
                                        именем <name1>
                message     <name>  <message>
                                        Отправить указанное сообщение сплетнице с
                                        именем <name>
                gossips
                                        Напечатать имена всех имеющихся сплетниц в
                                        алфавитном порядке;
                listeners   <name>
                                        Напечатать имена всех слушателей сообщений
                                        от сплетницы с именем <name> в алфавитном
                                        порядке
                quit
                                        Выйти из приложения
                about
                                        Вывести имя автора шедевра (разработанного
                                        приложения), его группу и подгруппу
                help
                                        Вывести список доступных команд в
                                        приложении
                """);
    }
}
