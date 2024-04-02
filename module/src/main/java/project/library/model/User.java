package project.library.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User {
    private String name;
    private int age;
    private String phoneNumber;
    private String email;
    private Address address;
    private boolean hasGuardianPermission;

    public User(String name, int age, String phoneNumber, String email) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public boolean isNeededGuardianPermission(User user){
        return user.getAge() < 18;
    }
}
