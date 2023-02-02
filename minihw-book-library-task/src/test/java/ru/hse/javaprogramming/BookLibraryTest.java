package ru.hse.javaprogramming;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookLibraryTest {

    @Test
    void noBooksInitially() {
        BookLibrary bookLibrary = new LibraryOne();

        assertEquals(0, bookLibrary.count());
    }

    @Test
    void bookSaved() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Effective Modern C++", "Scott Meyers"));

        assertNotNull(newBook.id());
    }

    @Test
    void renameBook() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Effective Modern C++", "Scott Meyers"));
        Book newBook1 = bookLibrary.saveOne(new Book(0L, "Effective Modern C#", "Scott Meyers"));

        assertNotNull(newBook.id());
        assertNotNull(newBook1);
        assertEquals(newBook.id(), newBook1.id());
        assertEquals(1, bookLibrary.count());
        assertEquals(1, bookLibrary.findByAuthorSortedByTitle("Scott Meyers").size());
        assertTrue(bookLibrary.findByAuthorSortedByTitle("Scott Meyers").contains(new Book(0L, "Effective Modern C#", "Scott Meyers")));
    }

    @Test
    void findAllBooksUnmodifiable() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Chamber of Secrets", "J. K. Rowling"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "Prisoner of Azkaban", "J. K. Rowling"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "Goblet of Fire", "J. K. Rowling"));
        Book newBook4 = bookLibrary.saveOne(new Book(null, "Order of the Phoenix", "J. K. Rowling"));
        Book newBook5 = bookLibrary.saveOne(new Book(null, "Half-Blood Prince", "J. K. Rowling"));
        Book newBook6 = bookLibrary.saveOne(new Book(null, "Deathly Hallows", "J. K. Rowling"));

        assertEquals(7, bookLibrary.count());

        Set<Book> bookSet = bookLibrary.findAll();

        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, () -> {
            bookSet.add(new Book(null, "This book definitely should throw", "N. Error"));
        });
    }

    @Test
    void findByIdBook() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));

        assertTrue(bookLibrary.findById(0L).isPresent());
        assertFalse(bookLibrary.findById(1L).isPresent());

        assertEquals(bookLibrary.findById(0L).get().author(), "J. K. Rowling");
        assertEquals(bookLibrary.findById(0L).get().title(), "Philosopher's Stone");
        assertEquals(bookLibrary.findById(0L).get(), new Book(0L, "Philosopher's Stone", "J. K. Rowling"));
    }

    @Test
    void findByAuthorSortedByTitleSortTest() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "DDd", "John Doe"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Ddd", "John Doe"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "d", "John Doe"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "111", "John Doe"));
        Book newBook4 = bookLibrary.saveOne(new Book(null, "CCC", "John Doe"));
        Book newBook5 = bookLibrary.saveOne(new Book(null, "AAA", "John Doe"));
        Book newBook6 = bookLibrary.saveOne(new Book(null, "333", "John Doe"));
        Book newBook7 = bookLibrary.saveOne(new Book(null, "aaa", "John Doe"));

        List<String> titles = new ArrayList<>();

        for (Book book : bookLibrary.findAll()) {
            titles.add(book.title());
        }
        Collections.sort(titles);

        for (int i = 0; i < titles.size(); i++) {
            assertEquals(titles.get(i), bookLibrary.findByAuthorSortedByTitle("John Doe").get(i).title());
        }
    }

    @Test
    void findByTitleContainingTest() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "The Art of War", "Sun Tzu"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Art Book", "Some Guy"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "Some Book: Part I", "John Doe"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));
        Book newBook4 = bookLibrary.saveOne(new Book(null, "Chamber of Secrets", "J. K. Rowling"));

        Set<Book> setBooks = new HashSet<>();
        setBooks.add(newBook);
        setBooks.add(newBook1);
        setBooks.add(newBook2);

        assertEquals(setBooks, bookLibrary.findByTitleContaining("Art"));
    }

    @Test
    void findBookByAuthorMapTest() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Chamber of Secrets", "J. K. Rowling"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "Prisoner of Azkaban", "J. K. Rowling"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "Goblet of Fire", "J. K. Rowling"));
        Book newBook4 = bookLibrary.saveOne(new Book(null, "Order of the Phoenix", "J. K. Rowling"));
        Book newBook5 = bookLibrary.saveOne(new Book(null, "Half-Blood Prince", "J. K. Rowling"));
        Book newBook6 = bookLibrary.saveOne(new Book(null, "Deathly Hallows", "J. K. Rowling"));

        Book newBook7 = bookLibrary.saveOne(new Book(null, "AAA", "John Doe"));
        Book newBook8 = bookLibrary.saveOne(new Book(null, "333", "John Doe"));
        Book newBook9 = bookLibrary.saveOne(new Book(null, "aaa", "John Doe"));

        Book newBook10 = bookLibrary.saveOne(new Book(null, "Effective Modern C++", "Scott Meyers"));

        assertEquals(3, bookLibrary.findBookByAuthor().get("John Doe").size());
        assertEquals(1, bookLibrary.findBookByAuthor().get("Scott Meyers").size());
        assertEquals(7, bookLibrary.findBookByAuthor().get("J. K. Rowling").size());
        assertEquals(11, bookLibrary.count());
    }

    @Test
    void deleteByIdTest() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Chamber of Secrets", "J. K. Rowling"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "aaa", "John Doe"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "Effective Modern C++", "Scott Meyers"));

        assertTrue(bookLibrary.findById(0).isPresent());
        bookLibrary.deleteById(0);
        assertFalse(bookLibrary.findById(0).isPresent());

        assertTrue(bookLibrary.findById(1).isPresent());
        bookLibrary.deleteById(1);
        assertFalse(bookLibrary.findById(1).isPresent());

        assertTrue(bookLibrary.findById(2).isPresent());
        bookLibrary.deleteById(2);
        assertFalse(bookLibrary.findById(2).isPresent());

        assertTrue(bookLibrary.findById(3).isPresent());
        bookLibrary.deleteById(3);
        assertFalse(bookLibrary.findById(3).isPresent());
    }

    @Test
    void deleteAllTest() {
        BookLibrary bookLibrary = new LibraryOne();

        Book newBook = bookLibrary.saveOne(new Book(null, "Philosopher's Stone", "J. K. Rowling"));
        Book newBook1 = bookLibrary.saveOne(new Book(null, "Chamber of Secrets", "J. K. Rowling"));
        Book newBook2 = bookLibrary.saveOne(new Book(null, "Prisoner of Azkaban", "J. K. Rowling"));
        Book newBook3 = bookLibrary.saveOne(new Book(null, "Goblet of Fire", "J. K. Rowling"));
        Book newBook4 = bookLibrary.saveOne(new Book(null, "Order of the Phoenix", "J. K. Rowling"));
        Book newBook5 = bookLibrary.saveOne(new Book(null, "Half-Blood Prince", "J. K. Rowling"));
        Book newBook6 = bookLibrary.saveOne(new Book(null, "Deathly Hallows", "J. K. Rowling"));

        Book newBook7 = bookLibrary.saveOne(new Book(null, "AAA", "John Doe"));
        Book newBook8 = bookLibrary.saveOne(new Book(null, "333", "John Doe"));
        Book newBook9 = bookLibrary.saveOne(new Book(null, "aaa", "John Doe"));

        Book newBook10 = bookLibrary.saveOne(new Book(null, "Effective Modern C++", "Scott Meyers"));

        assertEquals(3, bookLibrary.findBookByAuthor().get("John Doe").size());
        assertEquals(1, bookLibrary.findBookByAuthor().get("Scott Meyers").size());
        assertEquals(7, bookLibrary.findBookByAuthor().get("J. K. Rowling").size());
        assertEquals(11, bookLibrary.count());

        bookLibrary.deleteAll();
        assertEquals(0, bookLibrary.count());
    }
}