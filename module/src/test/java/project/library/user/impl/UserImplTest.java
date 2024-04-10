package project.library.user.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.library.Library;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;
import project.library.model.book.features.Genre;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserImplTest {
    private UserImpl userImpl;
    private List<Book> bookList;
    private List<Book> userCartBooks;

    @BeforeEach
    void setUp() {
        userImpl = new UserImpl();
        bookList = new Library().getBooks();
        userCartBooks = userImpl.getCartBooks();
    }

    @Test
    void add() throws BookNotFoundException {
        //given
        Book book = new Book("The Sun and Her Flowers", "Rupi Kaur", Genre.POETRY, 2017);

        //when
        userImpl.add(book);

        //then
        Assertions.assertTrue(bookList.contains(book), "Failed to add book to library.");
    }

    @Test
    void remove() {
        //given
        Book book = new Book("The Sun and Her Flowers", "Rupi Kaur", Genre.POETRY, 2017);

        //when
        userImpl.remove(book);

        //then
        Assertions.assertFalse(bookList.contains(book), "Failed to remove book from library.");
    }

    @Test
    void borrow() {
        String errorMessage = "Failed to borrow book.";

        //given
        Book book = new Book("The Sun and Her Flowers", "Rupi Kaur", Genre.POETRY, 2017);

        //when
        userImpl.borrow(book);

        //then
        Assertions.assertAll(
                () -> assertFalse(bookList.contains(book), errorMessage + " Book still remains in library list."),
                () -> assertTrue(userCartBooks.contains(book), errorMessage + " No such book in user's cart " + book)
        );

    }

    @Test
    void returnBack() {
        String errorMessage = "Failed to return book.";

        //given
        Book book = new Book("The Sun and Her Flowers", "Rupi Kaur", Genre.POETRY, 2017);

        //when
        userImpl.returnBack(book);

        //then
        Assertions.assertAll(
                () -> assertTrue(bookList.contains(book), errorMessage + " No such book in library list " + book),
                () -> assertFalse(userCartBooks.contains(book), errorMessage + " Book remains in user's cart.")
        );
    }
}