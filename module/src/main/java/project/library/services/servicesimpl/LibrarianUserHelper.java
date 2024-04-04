package project.library.services.servicesimpl;

import project.library.Library;
import project.library.exception.UserInfoMissingException;
import project.library.model.Book;
import project.library.model.User;
import project.library.services.LibrarianUserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LibrarianUserHelper implements LibrarianUserServices {
    private static final Logger LOGGER = Logger.getLogger(LibrarianUserHelper.class.getName());

    private Library library;
    private List<Book> books;
    public List<User> users;

    public LibrarianUserHelper() {
        library = new Library();
        users = new ArrayList<>();
        books = library.books;
    }

    @Override
    public void registerUser(User user){
        if (isRegistered(user)) {
            LOGGER.log(Level.INFO, "User is already registered.");
        }
        if (user == null) {
            UserInfoMissingException e = new UserInfoMissingException();
            LOGGER.log(Level.WARNING, e.getMessage());
        } else {
            users.add(user);
            LOGGER.log(Level.INFO, "REGISTERED SUCCESSFULLY " + user.getName() + "!");
        }
    }

    @Override
    public User retrieveUserInfo(Long id) throws UserInfoMissingException {
        User user = users
                .stream()
                .filter(reader -> Objects.equals(reader.getId(), id))
                .findFirst()
                .orElseThrow(UserInfoMissingException::new);

        LOGGER.info("User was successfully found");
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("USER LIST");
        return users;
    }

    @Override
    public void removeUser(User user) {
        if (user != null && users.contains(user)) {
            users.remove(user);
            LOGGER.info("Successfully removed user " + user.getName() + ".");
        }
    }

    @Override
    public boolean isRegistered(User user) {
        return users.contains(user);
    }
}
