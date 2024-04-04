package project.library.services.servicesimpl;

import project.library.Library;
import project.library.connection.DatabaseManager;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;
import project.library.model.book.features.Genre;
import project.library.services.LibrarianBookServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarianBookHelper implements LibrarianBookServices {
    private static final Logger LOGGER = Logger.getLogger(LibrarianBookHelper.class.getName());

    private Connection connection = DatabaseManager.connectToDatabase();
    List<Book> books = new Library().books;

    public LibrarianBookHelper() {
    }

    @Override
    public void add(Book book) throws SQLException {
        if (book != null) {
            books.add(book);

            String title = book.getTitle();
            String author = book.getAuthor();
            Genre genre = book.getGenre();
            String nameGenre = genre.name();
            int year = book.getPublishedYear();

            Long aLong = generateId();

            String insertStatement = "INSERT INTO BOOKS(ID, TITLE, AUTHOR, RANK, PUBLISHED_YEAR) VALUES (?,?,?,?,?);";
            try (PreparedStatement ps = connection.prepareStatement(insertStatement)) {
                ps.setLong(1, aLong);
                ps.setString(2, title);
                ps.setString(3, author);
                ps.setString(4, nameGenre);
                ps.setInt(5, year);

                ps.executeUpdate();
            }
        }
    }

    @Override
    public Book read(Long id) throws BookNotFoundException {
        for (Book book : books) {
            if (Objects.equals(book.getId(), id)) {
                return book;
            } else {
                LOGGER.info("ID not found " + id);
                throw new BookNotFoundException("Failed to search book with id #" + id);
            }
        }
        return null;
    }

    @Override
    public Book update(Long id, Book book) {
        for (Book book1 : books) {
            if (Objects.equals(book1.getId(), id)) {
                book1.setTitle(book.getTitle());
                book1.setAuthor(book.getAuthor());
                book1.setGenre(book.getGenre());
                book1.setPublishedYear(book.getPublishedYear());
            }
        }
        return book;
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    public List<Book> organiseBooks(Genre genre) {
        LOGGER.info("Searching books with a specific genre: " + genre.name());
        List<Book> organisedBooks = new ArrayList<>();
        for (Book book : books) {
            if(book.getGenre().name().trim().equalsIgnoreCase(genre.name().trim())){
                organisedBooks.add(book);
            }
        }
        if (organisedBooks.isEmpty()) {
            LOGGER.log(Level.WARNING, "Please, contact with help in case something is missing");
            throw new NullPointerException("Books are absent with genre: " + genre.name() + "..");
        }
        return organisedBooks;
    }

    @Override
    public boolean validateBook(Book book) {
        return book != null && books.contains(book);
    }

    public Long generateId(){
        return new Random().nextLong(1_000_000);
    }
}
