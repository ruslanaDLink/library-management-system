package project.library.service;

import project.library.exception.BookNotFoundException;
import project.library.exception.UserInfoMissingException;
import project.library.model.Admin;
import project.library.model.Book;
import project.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private List<Book> bookList;
    private Admin admin;

    public UserService() {
        this.bookList = new ArrayList<>();
        admin = new Admin();
    }

    public void signUp(User user) throws UserInfoMissingException {
        List<User> users = admin.getUsers();
        if (users != null) {
            users.add(user);
            System.out.println("Welcome to library, " + user.getName() + "!");
        } else {
            throw new UserInfoMissingException();
        }
    }

    public boolean checkIfBookPresent(Book book) {
        return book != null;
    }

    public void addBookToList(Book book) throws BookNotFoundException {
        if (checkIfBookPresent(book)) {
            bookList.add(book);
        } else {
            String notFoundException = "The book was not found in the library system.";
            throw new BookNotFoundException(notFoundException, book);
        }
    }

    public void removeBookFromList(Book book) {
        if (bookList.contains(book)) {
            bookList.remove(book);
        }
    }
}
