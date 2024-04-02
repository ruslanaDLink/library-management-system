package project.library.model;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<User> users;

    public Admin() {
        this.users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }
}
