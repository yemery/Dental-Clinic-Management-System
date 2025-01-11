package org.example.presentation.view.frames.Appoitments;

import org.example.presentation.controller.AppointmentController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteAppointment {
    public DeleteAppointment(Long id, AppLayout appLayout) {
        AppointmentController controller = new AppointmentController();
        controller.deleteAppointment(id);

        appLayout.getNavbar().simulateTabClick("Appointments");
    }
}
