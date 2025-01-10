package org.example.presentation.view;

import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.PrescriptionMedicineController;

public class PrescriptionMedicineView {
    public static void main(String[] args) {
        PrescriptionMedicineController presMedController = new PrescriptionMedicineController();
//        Integer minQuantity, Integer maxQuantity, String description, Long medicine
        PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine(1,12,"desc",64L);
        PrescriptionMedicine prescriptionMedicine1 = new PrescriptionMedicine(1,12,"desc1",99L);
        presMedController.addPrescriptionMedicine(prescriptionMedicine);
        presMedController.addPrescriptionMedicine(prescriptionMedicine1);

        System.out.println(presMedController.displayAllPrescriptionMedicine());
    }
}
