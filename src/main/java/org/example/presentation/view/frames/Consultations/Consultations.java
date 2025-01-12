package org.example.presentation.view.frames.Consultations;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Consultations extends JPanelContainer {
    public Consultations(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditConsultation(id,appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteConsultation(id,appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowConsultation(id);
    }
}
