package org.example.presentation.view.frames.Prescriptions;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Prescriptions extends JPanelContainer {
    public Prescriptions(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onShow(Long id) {
        new ShowPrescription(id);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeletePrescription(id, appLayout);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditPrescription(id, appLayout);
    }
}
