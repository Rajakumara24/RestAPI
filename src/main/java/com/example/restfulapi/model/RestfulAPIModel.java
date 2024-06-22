package com.example.restfulapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity                       // class is an entity and is mapped to a database table.
@Table(name = "signup")
public class RestfulAPIModel {
    @Id                      //This annotation specifies the primary key of the entity.
    private String email;
    private String name;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RestfulAPIModel(String email, String name, String password) { //when a new user signs up, you would use this constructor to create a new RestfulAPIModel object with the user's email, name, and password.
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public RestfulAPIModel() {    //no-argument constructor is required by JPA for it to create instances of your entity class when retrieving data from the database.
    }
}
