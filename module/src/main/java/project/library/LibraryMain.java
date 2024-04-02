package project.library;

import project.library.connection.DatabaseManager;
import project.library.exception.BookNotFoundException;
import project.library.model.Book;
import project.library.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryMain {
    public static List<Book> books;
    private DatabaseManager databaseManager;

    public static void main(String[] args) throws BookNotFoundException {
        UserService userService = new UserService();
        LibraryMain library = new LibraryMain();
//        Book book = new Book("And Then There Were None", "Agatha Christie", "978-0062073488", Genre.THRILLER);
//        userService.addBookToList(book);
        System.out.println(library.getAllBooksFromDatabase());
    }

    public LibraryMain(){
        books = new ArrayList<>();
        databaseManager = new DatabaseManager();
    }

    public List<Book> getAllBooksFromDatabase() {
        try (Connection connection = databaseManager.connectToDatabase()) {
            String selectAllBooksStatement = "SELECT * FROM BOOK;";
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllBooksStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            int index = 0;
            while (resultSet.next()) {
                books.add(resultSet.getObject(index, Book.class));
                index++;
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
