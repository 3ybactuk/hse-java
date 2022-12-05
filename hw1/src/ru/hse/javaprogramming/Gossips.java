package ru.hse.javaprogramming;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class Gossips {
    public static int MAX_MOVES;
    public static final int MAX_GOSSIPERS = 100;
    public static SortedSet<Gossiper> gossipers = new TreeSet<Gossiper>(
            (Gossiper g1, Gossiper g2) -> -(g1.name).compareTo(g2.name));

    public static void main(String[] args) {
        try {
            MAX_MOVES = Integer.parseUnsignedInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Error: CLI argument m must be an unsigned integer number.");
            return;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: CLI argument m must be passed to program.");
            return;
        }

        iohandler();
    }

    /**
     * Handles user command input.
     */
    public static void iohandler() {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.println("Waiting for input... type \"help\" to see info about commands.");
            System.out.print("> ");
            input = scanner.nextLine();
            String[] cmd = input.split("\\s+");

            try {
                switch (cmd[0]) {
                    case "create" -> create(cmd[1], cmd[2]);
                    case "link" -> linkOrUnlink(cmd[1], cmd[2], true);
                    case "unlink" -> linkOrUnlink(cmd[1], cmd[2], false);
                    case "message" -> message(cmd[1], cmd[2]);
                    case "gossips" -> gossips();
                    case "listeners" -> listeners(cmd[1]);
                    case "about" -> about();
                    case "help" -> help();
                    case "quit" -> {
                        quit();
                        return;
                    }
                    default -> System.out.println("Error: unknown command.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Error: incorrect arguments for command \"" + cmd[0] + "\".");
            }
        }
    }

    /**
     * Поиск сплетницы
     * @param name имя сплетницы
     * @return возвращает сплетницу с именем name, иначе null
     */
    public static Gossiper findByName(String name) {
        for (Gossiper gossiper : gossipers) {
            if (name.equals(gossiper.name)) {
                return gossiper;
            }
        }

        return null;
    }

    /**
     * Создать экземпляр сплетницы с указанным
     * именем name.
     * Возможные значения type:
     * 1. null
     * 2. censor
     * 3. spammer
     * 4. simple
     * 5. deduplicator
     * Максимальное количество сплетниц – 100. При
     * превышении максимального числа сплетниц
     * приложение печатает сообщение об ошибке и
     * ожидает ввод новой команды
     * @param name имя сплетницы
     * @param type тип сплетницы
     */
    public static void create(String name, String type) {
        if (findByName(name) != null) {
            System.out.println("Error: name already taken.");
            return;
        }

        if (gossipers.size() >= 100) {
            System.out.println("Error: maximum amount of gossipers reached (" + MAX_GOSSIPERS + ").");
            return;
        }

        switch (type) {
            case "null" -> gossipers.add(new NullGossiper(name));
            case "censor" -> gossipers.add(new CensorGossiper(name));
            case "spammer" -> gossipers.add(new SpammerGossiper(name));
            case "simple" -> gossipers.add(new SimpleGossiper(name));
            case "deduplicator" -> gossipers.add(new DeduplicatorGossiper(name));
            default -> {
                System.out.println("Error: unknown type.");
                return;
            }
        }

        System.out.println(name + " successfully created.");
    }

    /**
     * Привязывает или отвязывает слушателя от вещателя
     * @param name1 сплетница-вещатель
     * @param name2 сплетница-слушатель
     * @param isLink true = привязка; false = отвязка
     */
    public static void linkOrUnlink(String name1, String name2, boolean isLink) {
        Gossiper talker = findByName(name1);
        Gossiper listener = findByName(name2);

        if (talker == null) {
            System.out.println("Error: name \"" + name1 + "\" not found.");
            return;
        }

        if (listener == null) {
            System.out.println("Error: name \"" + name2 + "\" not found.");
            return;
        }

        if (isLink) {
            talker.addListener(listener);
        } else {
            talker.removeListener(listener);
        }
    }

    /**
     * Отправить указанное сообщение сплетнице с
     * именем <name>
     * @param name имя сплетницы
     * @param message текст сообщения
     */
    public static void message(String name, String message) {
        Gossiper talker = findByName(name);

        if (talker == null) {
            System.out.println("Error: name \"" + name + "\" not found.");
            return;
        }

        talker.getGossipMessage(message);
    }

    /**
     * Напечатать имена всех имеющихся сплетниц в
     * алфавитном порядке;
     */
    public static void gossips() {
        for (Gossiper gossiper : gossipers) {
            System.out.println(gossiper.name);
        }
    }

    /**
     * Напечатать имена всех слушателей сообщений
     * от сплетницы с именем <name> в алфавитном
     * порядке
     * @param name имя сплетницы-вещателя
     */
    public static void listeners(String name) {
        Gossiper talker = findByName(name);

        if (talker == null) {
            System.out.println("Error: name \"" + name + "\" not found.");
            return;
        }

        for (Gossiper gossiper : talker.listeners) {
            System.out.println(gossiper.name);
        }
    }

    /**
     * Завершение работы приложения
     */
    public static void quit() {
        System.out.println("See you again!");
    }

    /**
     * Author's credentials
     */
    public static void about() {
        System.out.println("""
                Работу выполнил:
                Студент группы БПИ211-2
                Шубин Никита Васильевич.
                """);
    }

    /**
     * Prints out all possible commands and how to use them
     */
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
