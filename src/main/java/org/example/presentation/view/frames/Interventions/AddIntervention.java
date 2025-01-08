package org.example.presentation.view.frames.Interventions;

import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

public class AddIntervention extends Frame {
    private Input price = new Input("Price", String.class);
    private AppLayout appLayout;

    public AddIntervention(AppLayout appLayout) {
        super();
    }
}
