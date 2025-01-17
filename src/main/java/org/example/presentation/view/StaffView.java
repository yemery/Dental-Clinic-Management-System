package org.example.presentation.view;

import org.example.model.Assistant;
import org.example.model.Doctor;
import org.example.model.Staff;
import org.example.model.enums.UserType;
import org.example.presentation.controller.StaffController;

public class StaffView {
    public static void main(String[] args) {
        StaffController staffController = new StaffController();
        Doctor doctor = new Doctor("doctor","pwd",22000,"speciality1", UserType.DOCTOR);
        System.out.println(doctor);

//        Doctor doctor1 = new Doctor("doctor1","pwd",22000,"speciality1", UserType.DOCTOR);
//        Assistant assistant = new Assistant("assistant", "assistant", 5500, 1000D, UserType.ASSISTANT);
//        System.out.println(assistant);
//        staffController.addUser(assistant);
////        userController.addUser(doctor);
//        staffController.addUser(doctor1);

//        staffController.deleteUser(17L);
//        Staff u = staffController.getUser(17L);
//        u.setSalary(999999);
//        staffController.updateUser(u);
//        System.out.println(staffController.displayUsers());



        staffController.displayUsers().forEach(System.out::println);
    }
}
