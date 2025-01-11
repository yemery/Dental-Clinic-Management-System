package org.example.presentation.view.frames.Patient;

import org.example.presentation.view.frames.Acts.DeleteAct;
import org.example.presentation.view.frames.Acts.EditAct;
import org.example.presentation.view.frames.Acts.ShowAct;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Patients extends JPanelContainer{
    public Patients(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
//        new EditPatient(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
//        new DeletePatient(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowPatient(id);
    }
}