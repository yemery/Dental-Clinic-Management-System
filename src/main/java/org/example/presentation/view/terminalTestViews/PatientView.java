package org.example.presentation.view.terminalTestViews;

import org.example.model.Patient;
import org.example.presentation.controller.PatientController;

public class PatientView {
    public static void main(String[] args) {
        PatientController patientController = new PatientController();

//        Patient p1 = new Patient("meryem", "yemery", "01552", LocalDate.of(2003,07,17), "morocco", "0000", "yem@mil.com", Gender.FEMALE, "R842151", Mutuelle.CNSS, "student");
//        Patient p2 = new Patient("fatima", "ff", "01562", LocalDate.of(2003,07,17), "morocco", "0000", "yem@mil.com", Gender.MALE, "R842151", Mutuelle.CNOPS, "teacher");
//
//        patientController.addPatient(p1);
//        patientController.addPatient(p2);

        System.out.println(patientController.displayPatients()  );
        System.out.println(patientController.getPatient(84L)    );

        Patient p1= patientController.getPatient(84L);
        p1.setFirstName("update first name");
        patientController.updatePatient(p1);

        System.out.println(patientController.getPatient(84L)    );

        patientController.deletePatient(84L);




    }
}
