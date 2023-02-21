package ru.hse.javaprogramming;

import java.util.Objects;

public record Book (
        Long id,
        String title,
        String author
) implements Comparable<Book> {
    public Book {

        if (title == null) {
            throw new IllegalArgumentException("title == null");
        }
        if (author == null) {
            throw new IllegalArgumentException("author == null");
        }


    }

    @Override
    public int compareTo(Book o) {
        if (id.equals(o.id())) {
            return 0;
        }
        return title.compareTo(o.title());
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}