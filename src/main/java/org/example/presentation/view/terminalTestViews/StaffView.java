package org.example.presentation.view.terminalTestViews;

import org.example.model.Doctor;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;
import org.example.presentation.controller.StaffController;

import java.time.LocalDate;

public class StaffView {
    public static void main(String[] args) {
        StaffController staffController = new StaffController();
        Doctor doctor = new Doctor("doc", "doc1", "ABC0000", LocalDate.now(),"address","000","sd@S0.s", Gender.MALE,"user", "pwd", 5000, UserType.DOCTOR);
        Doctor doctor2 = new Doctor("doc", "doc1", "ABC0000", LocalDate.now(),"address","000","sd@S0.s", Gender.MALE,"user", "pwd", 5000, UserType.DOCTOR);

        staffController.addUser(doctor);
        staffController.addUser(doctor2);
    }
}
