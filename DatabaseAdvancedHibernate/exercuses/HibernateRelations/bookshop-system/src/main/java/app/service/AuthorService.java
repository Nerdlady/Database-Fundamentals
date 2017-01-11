package app.service;

import app.domain.Author;

import java.util.Date;

public interface AuthorService {
    void save(Author author);

    void delete(Author author);

    void delete(Long id);

    Author findAuthor(Long id);

    Iterable<Author> findAuthors();

    Iterable<Author> findAllWitBookBefore(Date date);

    Iterable<Author> findAllOrderByCountOfBooks();

    Author findAuthorByLastName(String lastName);

    Iterable<Author> findAllByFirstNameEndsWith(String pattern);

    Integer getAuthorsBooksCount(String firstName, String lastName);
}
