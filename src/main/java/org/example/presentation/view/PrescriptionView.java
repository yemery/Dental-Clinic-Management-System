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
        // wait

        // crud test

        // we dug is false a l okht shakespear
        // wa u said we dug ana glt lik rah ghalta
        // nvm

//        prescriptionC.deletePrescription(p1);
//        prescriptionC.displayPrescriptions();
        // ana w 3yit la 3yit 11:30 ylh ntfrj fshi7aja wn3s

    }
}
