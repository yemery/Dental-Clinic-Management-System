package org.example.presentation.view;

import org.example.model.*;
import org.example.model.enums.*;
import org.example.presentation.controller.MedicalCaseController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MedicalCaseView {
    public static void main(String[] args) {
        MedicalCaseController medicalCaseController = new MedicalCaseController();
        MedicalCase md= new MedicalCase(1L,
                List.of(
                        17L,16L
                ),List.of(
                        17L,177L
        ));
//
        medicalCaseController.addMedicalCase(md);
//        System.out.println(medicalCaseController.getAllMedicalCase()        );
////        medicalCaseController.getMedicalCaseById();
//
//        MedicalCase md2= medicalCaseController.getMedicalCaseById(177L);
//        md2.setPatient(122L);
//        medicalCaseController.updateMedicalCase(md2);
//        System.out.println(medicalCaseController.getMedicalCaseById(177L));

        medicalCaseController.deleteMedicalCase(177L);
//        System.out.println(md2);

}
}
