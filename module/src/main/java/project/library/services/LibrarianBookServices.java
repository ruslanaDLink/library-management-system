package project.library.services;

import project.library.exception.BookNotFoundException;
import project.library.model.Book;

import java.sql.SQLException;

public interface LibrarianBookServices {

    boolean validateBook(Book book);

    void add(Book book) throws SQLException;
    Book read(Long id) throws BookNotFoundException;
    Book update(Long id, Book book);
    void delete(Book book);
}
