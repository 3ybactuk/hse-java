package ru.hse.javaprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * StudentSelector contains the list of the students and methods to manipulate with them.
 */
public class StudentSelector {
    private final List<Student> studentList = new ArrayList<>();
    private static final Random random = new Random();

    /**
     * Generates random integer in range [min; max]
     * @param min left border
     * @param max right border
     * @return random integer in [min; max]
     */
    private static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    /**
     * Returns Student object by the student's last name
     * @param lastName last name of a student
     * @return Student object
     */
    private Student findByLastName(String lastName) {
        for (Student student : studentList) {
            if (student.getLastName().equals(lastName)) {
                return student;
            }
        }

        return null;
    }

    /**
     * @return size of the student list
     */
    public int count() {
        return studentList.size();
    }

    /**
     * @return true if list isn't empty, else false
     */
    public boolean hasStudents() {
        return count() > 0;
    }

    /**
     * Check if student with given last name exists
     * @param lastName last name of a student
     * @return true if such student exists, else false
     */
    public boolean existsByLastName(String lastName) {
        return findByLastName(lastName) != null;
    }

    /**
     * If the student doesn't already exist, adds a student to the list of the students
     * @param student Student object
     * @throws StudentAlreadyExistsException Exception if the student already exists
     */
    public void addStudent(Student student) throws StudentAlreadyExistsException {
        if (!existsByLastName(student.getLastName())) {
            studentList.add(student);
        } else {
            throw new StudentAlreadyExistsException("Студент с фамилией " + student.getLastName() + " уже существует.");
        }
    }

    /**
     * Removes a student from list by his last name.
     * @param lastName last name of a student
     * @return student if such exists, else null
     */
    public Student removeStudentByLastName(String lastName) {
        Student tempStudent = findByLastName(lastName);
        studentList.remove(tempStudent);
        return tempStudent;
    }

    /**
     * Randomly choose the student from the StudentSelector
     * @return Student object
     */
    public Student nextRandomStudent() {
        if (hasStudents()) {
            return studentList.get(randInt(0, count() - 1));
        }
        return null;
    }
}
