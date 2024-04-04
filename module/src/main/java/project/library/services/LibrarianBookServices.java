package project.library.services;

import project.library.model.Book;

import java.sql.SQLException;

public interface LibrarianBookServices {

    boolean validateBook(Book book);

    void addNewBookToCollection(Book book) throws SQLException;
}
