package org.example.presentation.view.frames.Acts;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acts extends JPanelContainer {

    public Acts(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditAct(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
    new DeleteAct(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowAct(id);
    }
}