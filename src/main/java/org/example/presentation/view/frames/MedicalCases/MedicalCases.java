package org.example.presentation.view.frames.MedicalCases;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class MedicalCases extends JPanelContainer {
    public MedicalCases(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditMedicalCase(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteMedicalCase(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowMedicalCase(id);
    }
}
