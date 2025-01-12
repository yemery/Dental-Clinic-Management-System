package org.example.presentation.view.frames.MedicalHistory;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class MedicalHistories extends JPanelContainer {
    public MedicalHistories(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditMedicalHistory(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteMedicalHistory(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowMedicalHistory(id);
    }
}
