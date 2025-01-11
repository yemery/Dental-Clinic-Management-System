package org.example.presentation.view.frames.Medicines;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Medicines extends JPanelContainer {
    public Medicines(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onShow(Long id) {
        new ShowMedicine(id);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteMedicine(id, appLayout);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditMedicine(id, appLayout);
    }
}
