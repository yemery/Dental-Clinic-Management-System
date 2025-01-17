package org.example.model;

import org.example.model.enums.Gender;
import org.example.model.enums.Status;
import org.example.model.enums.UserType;

import java.time.LocalDate;

public class Assistant extends Staff{
    private Double prime;

    public Assistant(){
        super();
    }
    public Assistant(Double prime) {
        this();
        this.prime = prime;
    }

    public Assistant(double salary, Double prime) {
        super(salary);
        this.prime = prime;
    }

    public Assistant(String username, String password, double salary, Double prime, UserType userType) {
        super(username, password, salary , userType);
        this.prime = prime;
    }

    public Assistant(String firstName, String lastName, String CIN, LocalDate birthDate, String address, String phone, String email, Gender gender, String username, String password, double salary, Status status, Double prime , UserType userType) {
        super(firstName, lastName, CIN, birthDate, address, phone, email, gender, username, password, salary, userType);
        this.prime = prime;
    }

    public Double getPrime() {
        return prime;
    }

    public void setPrime(Double prime) {
        this.prime = prime;
    }

//    @Override
//    public String getType() {
//        return "Assistant";
//    }
@Override
public String toString() {
    return "Assistant{" +
            super.toString() +
            ", prime=" + prime +
            '}';
}
}
