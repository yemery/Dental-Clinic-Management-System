package org.example.presentation.view.frames.Medicines;

import org.example.presentation.controller.MedicineController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteMedicine {
    public DeleteMedicine(Long id, AppLayout appLayout) {
        MedicineController controller = new MedicineController();
        controller.deleteMedicine(id);

        appLayout.getNavbar().simulateTabClick("Medicines");
    }
}
