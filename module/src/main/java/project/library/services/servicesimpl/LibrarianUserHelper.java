package project.library.services.servicesimpl;

import project.library.Library;
import project.library.exception.UserInfoMissingException;
import project.library.model.Book;
import project.library.model.User;
import project.library.services.LibrarianUserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class LibrarianUserHelper implements LibrarianUserServices {
    private static final Logger LOGGER = Logger.getLogger(LibrarianUserHelper.class.getName());

    private Library library;
    private List<Book> books;
    private List<User> users;

    public LibrarianUserHelper() {
        library = new Library();
        users = new ArrayList<>();
        books = library.books;
    }

    @Override
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

    @Override
    public User retrieveUserInfo(Long id) throws UserInfoMissingException {
        User user = users.stream().filter(reader -> Objects.equals(reader.getId(), id)).findFirst().orElseThrow(UserInfoMissingException::new);
        LOGGER.info("User was successfully found");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public void removeUser(User user) {
        if (user != null && users.contains(user)) {
            users.remove(user);
            LOGGER.info("Successfully removed user " + user.getName() + ".");
        }
    }
}
