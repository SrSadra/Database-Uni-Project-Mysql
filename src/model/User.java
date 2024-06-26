package model;

import java.util.Date;

public class User {
    private String name;
    private String username;
    private String lastname;
    private String password;
    private Date birthdate;
    
    public User(String name , String username, String lastname, String password ,Date birthdate ){ // birthdate maybe modified
        this.name = name;
        this.username = username;
        this.lastname = lastname;
        this.password = password;
        this.birthdate = birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthdate() {
        return birthdate;
    }
}
