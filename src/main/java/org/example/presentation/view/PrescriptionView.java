package org.example.presentation.view;

import org.example.model.Medicine;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.PrescriptionController;

public class PrescriptionView {

    public static void main(String[] args) {
        PrescriptionController prescriptionC = new PrescriptionController();

        // db 7na bghina nsawbo prescription yak
        Prescription p1 = new Prescription();
        // then we made a prescriptionMedicine to fill the list
        PrescriptionMedicine prescrMed = new PrescriptionMedicine(12,12,"desc", new Medicine(82.0, "med 1", "desc 1"));
        // we added it
        p1.setPrescriptionsMedicine(prescrMed);
        // finally we added the prescription to the controller
        prescriptionC.addPrescription(p1);

        prescriptionC.displayPrescriptions();
        System.out.println(p1.getPrescriptionsMedicine().size());

        prescriptionC.removePrescriptionMedicine(p1,prescrMed);
        System.out.println(p1.getPrescriptionsMedicine().size());
//        prescriptionC.deletePrescription(p1);
        prescriptionC.displayPrescriptions();

    }
}
