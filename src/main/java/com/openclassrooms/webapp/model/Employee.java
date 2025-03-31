package com.openclassrooms.webapp.model;


import lombok.Data;

@Data
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

}
