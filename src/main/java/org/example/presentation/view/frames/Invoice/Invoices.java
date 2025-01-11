package org.example.presentation.view.frames.Invoice;

import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;

import java.awt.event.ActionListener;

public class Invoices extends JPanelContainer {
    public Invoices(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    protected void onShow(Long id) {
        new ShowInvoice(id);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteInvoice(id, appLayout);
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditInvoice(id, appLayout);
    }
}
