package ru.hse.javaprogramming;

import java.util.*;

public class LibraryOne implements BookLibrary {
    private final Set<Book> books = new LinkedHashSet<>();

    /**
     * Сохранить данную книгу в библиотеке.
     * <p>
     * Если {@link Book#id()} возвращает {@code null} у параметра, то эта книга прежде не была сохранена в библиотеке.
     * Тогда нужно запомнить книгу и вернуть {@code new Book(...)}, у которого будет проставлен {@code id}.
     * <p>
     * Иначе - обновить информацию о книге в библиотеке.
     * Например, мы могли поменять название у книги
     *
     * @param book книга для сохранения
     * @return сохранённая книга
     */
    @Override
    public Book saveOne(Book book) {
        Book newBook = null;
        if (book.id() == null) {
            newBook = new Book(count(), book.title(), book.author());
            books.add(newBook);
        } else {
            Optional<Book> optBook = findById(book.id());
            if (optBook.isPresent()) {
                newBook = optBook.get();
                books.remove(newBook);
                newBook = new Book(book.id(), book.title(), book.author());
                books.add(newBook);
            }
        }

        return newBook;
    }

    /**
     * Найти все книги, которые сохранены в библиотеке.
     * <p>
     * Возвращает неизменяемое множество
     *
     * @return множество всех книг в библиотеке
     */
    @Override
    public Set<Book> findAll() {
        return Collections.unmodifiableSet(books);
    }

    /**
     * Найти книгу по {@code id}
     *
     * @param id идентификатор книги
     * @return {@link Optional} с книгой, если книга с таким {@code id} существует, иначе - {@link Optional#empty()}
     */
    @Override
    public Optional<Book> findById(long id) {
        for (Book book : books) {
            if (book.id() == id) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    /**
     * Найти все книги автора
     *
     * @param author для поиска
     * @return список всех книг автора, отсортированный по названию книги
     */
    @Override
    public List<Book> findByAuthorSortedByTitle(String author) {
        Set<Book> results = new TreeSet<>();
        for (Book book : books) {
            if (book.author().toLowerCase().contains(author.toLowerCase())) {
                results.add(book);
            }
        }

        return new ArrayList<>(results);
    }

    /**
     * Найти все книги, у которых название содержит {@code titleSubstring}
     *
     * @param titleSubstring подстрока для поиска
     * @return множество найденных книг
     */
    @Override
    public Set<Book> findByTitleContaining(String titleSubstring) {
        Set<Book> results = new LinkedHashSet<>();
        for (Book book : books) {
            if (book.title().toLowerCase().contains(titleSubstring.toLowerCase())) {
                results.add(book);
            }
        }

        return results;
    }

    /**
     * Найти все книги по авторам.
     * <p>
     * Вернуть {@link Map} из {@link Book#author()} во множество книг данного автора.
     * <p>
     * Пример:
     * <p>
     * "Joshua Bloch" -> ["Effective Java", "Java puzzlers"]
     * "Дарья Донцова" -> [бесконечный список из книг]
     *
     * @return {@link Map} книг по авторам
     */
    @Override
    public Map<String, Set<Book>> findBookByAuthor() {
        Map<String, Set<Book>> results = new HashMap<>();

        for (Book book : books) {
            if (results.containsKey(book.author())) {
                results.get(book.author()).add(book);
            } else {
                results.put(book.author(), new HashSet<>() {{ add(book); }} );
            }
        }

        return results;
    }

    /**
     * Удалить книгу по идентификатору
     *
     * @param id идентификатор для удаления
     */
    @Override
    public void deleteById(long id) {
        books.removeIf(book -> book.id().equals(id));
    }

    /**
     * Удалить все книги из библиотеки
     */
    @Override
    public void deleteAll() {
        books.clear();
    }

    /**
     * Получить количество книг сохранённых в библиотеке
     *
     * @return количество книг
     */
    @Override
    public long count() {
        return books.size();
    }
}
