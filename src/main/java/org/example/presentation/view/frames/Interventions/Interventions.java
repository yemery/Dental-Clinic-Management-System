package org.example.presentation.view.frames.Interventions;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.frames.Acts.AddAct;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Interventions extends JPanelContainer {
//    public Interventions(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
//        super(data, appLayout, addBtnLabel, colNames, add);
//    }


    public Interventions(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id ,AppLayout appLayout) {
        System.out.println(id);
    }

    @Override
    protected void onDelete(Long id , AppLayout appLayout) {
        System.out.println(id);
    }
}
