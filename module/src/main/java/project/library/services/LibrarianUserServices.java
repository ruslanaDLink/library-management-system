package project.library.services;

import project.library.exception.UserInfoMissingException;
import project.library.model.User;

import java.util.List;

public interface LibrarianUserServices {

    User registerUser(User user);

    void removeUser(User user);

    String retrieveUserInfo(Long id) throws UserInfoMissingException;

    List<User> getAllUsers();

    boolean isRegistered(User user);
}
