package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;

import java.time.LocalDate;
import java.util.Objects;


@JsonIgnoreProperties(ignoreUnknown = true)

public class User extends Person {
    protected String username;
    protected String password;
    protected UserType userType;

    public User(){
        super();
    }
    public User(String username, String password , UserType userType) {
        super();
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    public User(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password , UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender);
        this.username = username;
        this.password = password;
        this.userType = userType;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

}
