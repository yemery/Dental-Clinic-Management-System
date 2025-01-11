package org.example.presentation.view.frames.Appoitments;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Appointments extends JPanelContainer {

    public Appointments(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditAppointment(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteAppointment(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        System.out.println("show appointment");

    }
}
