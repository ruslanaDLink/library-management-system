package project.library.user;

import project.library.exception.BookNotFoundException;
import project.library.model.Book;

public interface UserInterface {

    void add(Book book) throws BookNotFoundException;

    void remove(Book book);

    void borrow(Book book);

    Book searchByTitle(String title);

    Book searchByAuthor(String author);

    Book searchByGenre(String genre);

    void returnBack(Book book);
}
