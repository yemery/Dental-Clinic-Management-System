package org.example.presentation.view.frames.PrescriptionsMedicines;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class PrescriptionMedicines extends JPanelContainer {
    public PrescriptionMedicines(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onShow(Long id) {
        new ShowPM(id);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeletePM(id, appLayout);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditPM(id, appLayout);
    }
}
