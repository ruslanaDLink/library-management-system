package project.library;

import project.library.connection.DatabaseManager;
import project.library.exception.UserInfoMissingException;
import project.library.model.Book;
import project.library.model.User;
import project.library.model.book.features.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Librarian {
    private static final Logger LOGGER = Logger.getLogger(Librarian.class.getName());

    private Connection connection = DatabaseManager.connectToDatabase();
    private Library library;
    private List<Book> books;
    private List<User> users;

    //task

    //should manage book list
    //should manage user list


    public Librarian() {
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

    public List<User> getAllUsers() {
        return users;
    }

    public User retrieveUserInfo(Long id) throws UserInfoMissingException {
        User user = users.stream().filter(reader -> Objects.equals(reader.getId(), id)).findFirst().orElseThrow(UserInfoMissingException::new);
        LOGGER.info("User was successfully found");
        return user;
    }

    public void registerUser(User user) {
        if (users.contains(user)) {
            LOGGER.info("User is already registered.");
        }
        if (user == null) {
            LOGGER.info("Information is intended for the user's registration.");
        } else {
            LOGGER.info("Successfully registered user " + user.getName() + "!");
            users.add(user);
        }
    }

    public void removeUser(User user) {
        if (user != null && users.contains(user)) {
            users.remove(user);
            LOGGER.info("Successfully removed user " + user.getName() + ".");
        }
    }
}
