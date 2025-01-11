package org.example.presentation.view;

import org.example.model.Medicine;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.PrescriptionController;

import java.util.List;

public class PrescriptionView {

    public static void main(String[] args) {
        PrescriptionController prescriptionC = new PrescriptionController();


//        Prescription p1 = new Prescription();


//        p1.setPrescriptionsMedicine(prescrMed.getId());
        prescriptionC.addPrescription(new Prescription());
        prescriptionC.addPrescription(new Prescription());
//
//        prescriptionC.displayPrescriptions();
//        System.out.println(p1.getPrescriptionsMedicine().size());
//
//        prescriptionC.removePrescriptionMedicine(p1,prescrMed);
//        System.out.println(p1.getPrescriptionsMedicine().size());
////        prescriptionC.deletePrescription(p1);
//        prescriptionC.displayPrescriptions();

    }
}
