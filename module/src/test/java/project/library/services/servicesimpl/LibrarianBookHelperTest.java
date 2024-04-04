package project.library.services.servicesimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.library.Library;
import project.library.LibraryMain;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;
import project.library.model.book.features.Genre;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianBookHelperTest {
    private List<Book> books;
    private LibrarianBookHelper librarianServices;

    @BeforeEach
    void setUp() {
        Library library = new Library();
        books = library.books;
        librarianServices = new LibrarianBookHelper();
    }

    @Test
    void read() throws BookNotFoundException, SQLException {
        //given
        Book book = new Book("A Crown of the Gods", "S.M.Gaither", Genre.LITERARY_FICTION, 2021);
        librarianServices.add(book);

        //when
        Book readBook = librarianServices.read(book.getId());

        //then
        Assertions.assertNotNull(readBook, "Method failed to read book details.");
    }

    @Test
    void update() {
        //given
        Book book = new Book("A Crown of the Gods", "S.M.Gaither", Genre.LITERARY_FICTION, 2021);

        //when
        Book updatedBook = librarianServices.update(book.getId(), new Book(book.getTitle(), book.getAuthor(), book.getGenre(), 2023));

        //then
        Assertions.assertNotEquals(book, updatedBook, "Failed to update.");
        Assertions.assertEquals(book.getId(), updatedBook.getId(), "ID are different.");
    }

    @Test
    void organiseBooks() throws SQLException {
        //given
        librarianServices.add(new Book("Gone Girl", "Gillian Flynn ", Genre.DETECTIVE, 2012));


        //when
        List<Book> detectiveBooks = librarianServices.organiseBooks(Genre.DETECTIVE);


        //then
        Assertions.assertNotNull(detectiveBooks, "The books are not organised.");
    }

    @Test
    void validateBook() {
        //given

        //when
        boolean value = librarianServices.validateBook(new Book("title", "author", Genre.CLASSICS, 2000));

        //then
        Assertions.assertFalse(value, "unexpected output.");
    }
}