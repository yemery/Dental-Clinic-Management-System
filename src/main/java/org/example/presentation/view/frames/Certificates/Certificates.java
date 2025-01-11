package org.example.presentation.view.frames.Certificates;

import org.example.presentation.view.frames.Interventions.DeleteIntervention;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.JPanelContainer;
import java.awt.event.ActionListener;

public class Certificates extends JPanelContainer {

    public Certificates(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
    }

    @Override
    public void updateUI() {
        super.updateUI();
    }

    @Override
    protected void onShow(Long id) {
        new ShowCertificate(id);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeleteCertificate();
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditCertificate(id, appLayout);
    }
}
