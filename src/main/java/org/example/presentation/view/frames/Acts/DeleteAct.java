package org.example.presentation.view.frames.Acts;

import org.example.presentation.controller.ActController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteAct {
    public DeleteAct(Long id, AppLayout appLayout) {
        ActController actController = new ActController();
        actController.deleteAct(id);

        appLayout.getNavbar().simulateTabClick("Acts");
    }
}
