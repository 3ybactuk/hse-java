package ru.hse.javaprogramming;

import java.util.Scanner;

public class Main {
    /**
     * Main function with input parsing
     * @param args CLI arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSelector studentSelector = createNewStudentSelector();
        String[] input;

        System.out.println("В группе " + studentSelector.count() + " студент(-ов)");
        System.out.println("""
                Введите:
                * next, чтобы узнать, кто пойдёт к доске
                * about, чтобы узнать автора программы
                * remove <фамилия>, чтобы убрать студента из выбора
                * exit, чтобы выйти
                """);
        while (true) {
            System.out.println("Ваш выбор?");
            System.out.print("> ");

            if (!scanner.hasNext()) {
                exit();
            }

            input = scanner.nextLine().split("\\s+");

            switch (input[0]) {
                case "next":
                    nextStudent(studentSelector);
                    break;
                case "about":
                    about();
                    break;
                case "remove":
                    if (input.length > 1) removeStudent(input[1], studentSelector); else unknownCommand();
                    break;
                case "exit":
                    exit();
                    return;
                default:
                    unknownCommand();
            }

            System.out.println("Введите «next», «remove <фамилия>», «about» или «exit»");
        }

    }

    /**
     * Creates new StudentSelector and fills it with some students
     * @return new StudentSelector object
     */
    public static StudentSelector createNewStudentSelector() {
        StudentSelector studentSelector = new StudentSelector();

        studentSelector.addStudent(new Student("Никита", "Шубин"));
        studentSelector.addStudent(new Student("Алла", "Никифорова"));
        studentSelector.addStudent(new Student("Василиса", "Григорьева"));
        studentSelector.addStudent(new Student("Артем", "Степанов"));
        studentSelector.addStudent(new Student("Владислав", "Смирнов"));
        studentSelector.addStudent(new Student("Иван", "Строганов"));
        studentSelector.addStudent(new Student("Алсу", "Хабибулина"));
        studentSelector.addStudent(new Student("Петя", "Сальников"));
        studentSelector.addStudent(new Student("Кирилл", "Шабад"));
        studentSelector.addStudent(new Student("Алиса", "Хабибрахманова"));
        studentSelector.addStudent(new Student("Иван", "Воропаев"));
        studentSelector.addStudent(new Student("Максим", "Стрельцов"));
        studentSelector.addStudent(new Student("Иван", "Курихин"));
        studentSelector.addStudent(new Student("Владислав", "Самсонов"));
        studentSelector.addStudent(new Student("Вячеслав", "Рыжков"));
        studentSelector.addStudent(new Student("Никита", "Ермишин"));

        return studentSelector;
    }

    /**
     * Removes student by last name from student selector
     * @param lastName last name of the student to be removed
     * @param studentSelector student selector instance
     */
    public static void removeStudent(String lastName, StudentSelector studentSelector) {
        Student student = studentSelector.removeStudentByLastName(lastName);
        if (student != null) {
            System.out.println("Ок, \"" + student.toString() + "\" к доске больше не пойдёт. В группе " + studentSelector.count() + " студент(-ов)");
        } else {
            System.out.println("Такого студента нет");
        }
    }

    /**
     * Randomly choose the next student from the StudentSelector
     * @param studentSelector StudentSelector object
     */
    public static void nextStudent(StudentSelector studentSelector) {
        Student student = studentSelector.nextRandomStudent();
        if (student != null) {
            System.out.println("К доске идёт " + studentSelector.nextRandomStudent().toString());
        } else {
            System.out.println("Студентов не осталось!");
        }
    }

    /**
     * If the command isn't supported, outputs error message.
     */
    public static void unknownCommand() {
        System.out.println("Неизвестная команда.");
    }

    /**
     * Outputs info about the author and a silly little joke
     */
    public static void about() {
        System.out.println("""
                Работу выполнил:
                Студент группы БПИ211-2
                Шубин Никита Васильевич.
                
                Вот короткая шутка от chatGPT:
                
                Why was the Java programmer always calm?
                Because he had a Borzoi dog to catch all of his exceptions!
                """);
    }

    /**
     * Contains exit message
     */
    public static void exit() {
        System.out.println("До свидания!");
    }
}
