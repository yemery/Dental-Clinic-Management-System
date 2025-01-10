package org.example.model;

import org.example.model.enums.Gender;

import java.time.LocalDate;
import java.util.Objects;

public abstract class User extends Person {
    protected String username;
    protected String password;

    public User(){
        super();
    }
    public User(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender);
        this.username = username;
        this.password = password;
    }



    public final String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public final String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(username, password);
//    }


    @Override
    public String toString() {
        return  super.toString() +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
