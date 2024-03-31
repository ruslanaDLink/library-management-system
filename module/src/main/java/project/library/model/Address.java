package project.library.model;

import lombok.Data;

@Data
public class Address {
    private String country;
    private String postCode;
    private String city;
    private String street;
    private String flatNumber;
}
