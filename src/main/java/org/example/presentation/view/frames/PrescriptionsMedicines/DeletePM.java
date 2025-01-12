package org.example.presentation.view.frames.PrescriptionsMedicines;

import org.example.presentation.controller.PrescriptionMedicineController;
import org.example.presentation.view.layouts.AppLayout;

public class DeletePM {
    public DeletePM(Long id, AppLayout appLayout) {
        PrescriptionMedicineController controller = new PrescriptionMedicineController();
        controller.deletePrescriptionMedicine(id);

        appLayout.getNavbar().simulateTabClick("PrescriptionMedicines");
    }
}
