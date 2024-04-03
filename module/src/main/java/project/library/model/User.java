package project.library.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String name;
    private int age;
    private String phoneNumber;
    private String email;

    public User(Long id, String name, int age, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
