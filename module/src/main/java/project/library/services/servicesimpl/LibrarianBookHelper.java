package project.library.services.servicesimpl;

import project.library.Library;
import project.library.connection.DatabaseManager;
import project.library.model.Book;
import project.library.model.book.features.Genre;
import project.library.services.LibrarianBookServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarianBookHelper implements LibrarianBookServices {
    private static final Logger LOGGER = Logger.getLogger(LibrarianBookHelper.class.getName());

    private Connection connection = DatabaseManager.connectToDatabase();
    private Library library;
    private List<Book> books;

    public LibrarianBookHelper() {
        library = new Library();
        books = library.books;
    }

    public void addNewBookToCollection(Book book) throws SQLException {
        if (book != null) {
            books.add(book);

            Long id = book.getId();
            String title = book.getTitle();
            String author = book.getAuthor();
            Genre genre = book.getGenre();
            String nameGenre = genre.name();
            int year = book.getPublishedYear();

            String insertStatement = "INSERT INTO BOOK(ID, TITLE, AUTHOR, RANK, PUBLISHED_YEAR) VALUES (?,?,?,?,?);";
            try (PreparedStatement ps = connection.prepareStatement(insertStatement)) {
                ps.setLong(1, id);
                ps.setString(2, title);
                ps.setString(3, author);
                ps.setString(4, nameGenre);
                ps.setInt(5, year);

                ps.executeUpdate();

                connection.close();
            }
        }
    }

    public List<Book> organiseBooks(Genre genre) {
        System.out.println("Searching book with a specific genre: " + genre.name());
        List<Book> books = library.books;
        List<Book> organisedBooks = books.stream().filter(book -> book.getGenre() == genre).toList();
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
}