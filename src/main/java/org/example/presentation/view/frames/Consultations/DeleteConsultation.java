package org.example.presentation.view.frames.Consultations;

import org.example.presentation.controller.ConsultationController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteConsultation {
    public DeleteConsultation(Long id, AppLayout appLayout) {
        ConsultationController controller = new ConsultationController();
        controller.deleteConsultation(id);

        appLayout.getNavbar().simulateTabClick("Consultations");
    }
}
