package ru.hse.javaprogramming;

/**
 * Contains Student's info
 */
public class Student {
    private final String firstName;
    private final String lastName;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Outputs first and last names of the student
     * @return
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * @return first name of the student
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * @return last name of the student
     */
    public String getLastName() {
        return this.lastName;
    }
}
