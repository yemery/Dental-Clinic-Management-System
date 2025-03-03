package org.example.model;

import org.example.model.enums.Gender;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public abstract class Person {
    protected final Long id;
    protected String firstName;
    protected String lastName;
    protected String CIN;
    protected LocalDate birthDate;
    protected String Address;
    protected String phone;
    protected String email;
    protected Gender gender;


    public Person() {
        this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public Person(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender) {
        this(); // means calling the default constructor
        this.firstName = firstName;
        this.lastName = lastName;
        this.CIN = CIN;
        this.birthDate = birthDate;
        this.Address = address;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public final String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public final String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public final String getCIN() {
        return CIN;
    }

    public final void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public final LocalDate getBirthDate() {
        return birthDate;
    }

    public final void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public final String getAddress() {
        return Address;
    }

    public final void setAddress(String address) {
        Address = address;
    }

    public final String getPhone() {
        return phone;
    }

    public final void setPhone(String phone) {
        this.phone = phone;
    }

    public final String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public final Gender getGender() {
        return gender;
    }

    public final void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", CIN='" + CIN + '\'' +
                ", birthDate=" + birthDate +
                ", Address='" + Address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(CIN, person.CIN);

    }
}
