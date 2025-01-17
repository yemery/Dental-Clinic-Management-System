package org.example.presentation.view.frames.Staff;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Staffs extends JPanelContainer
{
    public Staffs(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditStaff(id, appLayout);
    }


    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteStaff(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowStaff(id);
    }
}
