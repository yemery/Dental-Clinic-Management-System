package org.example.presentation.view.frames.MedicalHistory;

import org.example.presentation.controller.MedicsHistoryController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteMedicalHistory {
    public DeleteMedicalHistory(Long id, AppLayout appLayout) {
        MedicsHistoryController controller = new MedicsHistoryController();
        controller.deleteMHistory(id);

        appLayout.getNavbar().simulateTabClick("MedicalHistories");
    }
}
