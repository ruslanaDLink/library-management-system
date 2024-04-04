package project.library;

import project.library.connection.DatabaseManager;
import project.library.model.Book;
import project.library.model.book.features.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LibraryMain {
    private static List<Book> books;

    public static void main(String[] args) {
        LibraryMain main = new LibraryMain();
        List<Book> allBooksFromDatabase = main.getAllBooksFromDatabase();
        for (Book book : allBooksFromDatabase) {
            System.out.println(book + "\n");
        }
    }

    public LibraryMain() {
        books = new Library().books;
    }

    public List<Book> getAllBooksFromDatabase() {
        try (Connection connection = DatabaseManager.connectToDatabase()) {
            String selectAllBooksStatement = "SELECT * FROM BOOKS;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllBooksStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String title = resultSet.getString("TITLE");
                String author = resultSet.getString("AUTHOR");
                String genre = resultSet.getString("RANK");
                int publishedYear = resultSet.getInt("PUBLISHED_YEAR");
                Book book = new Book(title, author, Genre.valueOf(genre.toUpperCase().trim()), publishedYear);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Book> getBooks() {
        return books;
    }

}
