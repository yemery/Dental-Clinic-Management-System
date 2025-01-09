package org.example.presentation.view.frames.Interventions;

import org.example.presentation.controller.ActController;
import org.example.presentation.controller.InterventionController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteIntervention {
    public DeleteIntervention(Long id, AppLayout appLayout) {
        InterventionController controller = new InterventionController();
        controller.deleteIntervention(id);

        appLayout.getNavbar().simulateTabClick("Interventions");
    }
}
