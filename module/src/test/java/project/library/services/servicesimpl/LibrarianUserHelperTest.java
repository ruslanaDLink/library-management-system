package project.library.services.servicesimpl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.library.exception.UserInfoMissingException;
import project.library.model.User;

import java.util.List;


class LibrarianUserHelperTest {
    private LibrarianUserHelper userHelper;
    private List<User> users;
    private User user;

    @BeforeEach
    void setUp() {
        userHelper = new LibrarianUserHelper();
        users = userHelper.getAllUsers();
        user = new User(67899L, "Rosalia Clerk", 23, "+1672819284", "rosyclerk387@gmail.com");
    }

    @Test
    void registerUser() {
        //given


        //when
        User user1 = userHelper.registerUser(user);

        //then
        Assertions.assertTrue(users.contains(user1), "Failed to register user #" + user1.getId());
    }

    @Test
    void retrieveUserInfo() throws UserInfoMissingException {
        //given


        //when
        User registeredUser = userHelper.registerUser(user);
        String userInfo = userHelper.retrieveUserInfo(registeredUser.getId());

        //then
        Assertions.assertNotNull(userInfo, "Error occurred retrieving user's info...");
        Assertions.assertEquals(user.toString(), userInfo, "Invalid data.");
    }

    @Test
    void getAllUsers() {
        //given

        //when
        List<User> allUsers = userHelper.getAllUsers();

        //then
        Assertions.assertNotNull(allUsers);
    }

    @Test
    void removeUser() {
        //given

        //when
        userHelper.removeUser(user);

        //then
        Assertions.assertFalse(users.contains(user));
    }

    @Test
    void isRegistered() {
        //given

        //when
        User registeredUser = userHelper.registerUser(user);
        boolean registered = userHelper.isRegistered(registeredUser);

        //then
        Assertions.assertTrue(registered, "User must be registered. Failure in system..");
    }
}