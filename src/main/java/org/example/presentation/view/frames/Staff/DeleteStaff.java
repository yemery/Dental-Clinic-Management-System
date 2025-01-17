package org.example.presentation.view.frames.Staff;

import org.example.presentation.controller.StaffController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteStaff {
    public DeleteStaff(Long id, AppLayout appLayout) {
        StaffController controller = new StaffController();
        controller.deleteUser(id);

        appLayout.getNavbar().simulateTabClick("Staff");
    }
}
