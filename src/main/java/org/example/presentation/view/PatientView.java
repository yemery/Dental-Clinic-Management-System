package org.example.presentation.view;

import org.example.model.Patient;
import org.example.model.enums.Gender;
import org.example.model.enums.Mutuelle;
import org.example.presentation.controller.PatientController;

import java.time.LocalDate;

public class PatientView {
    public static void main(String[] args) {
        PatientController patientController = new PatientController();

        Patient p1 = new Patient("meryem", "yemery", "01552", LocalDate.of(2003,07,17), "morocco", "0000", "yem@mil.com", Gender.FEMALE, "R842151", Mutuelle.CNSS, "student");
        Patient p2 = new Patient("fatima", "ff", "01562", LocalDate.of(2003,07,17), "morocco", "0000", "yem@mil.com", Gender.MALE, "R842151", Mutuelle.CNOPS, "teacher");

        patientController.addPatient(p1);
        patientController.addPatient(p2);

        patientController.displayPatient();
        patientController.deletePatient(p1);

        patientController.displayPatient();

    }
}
