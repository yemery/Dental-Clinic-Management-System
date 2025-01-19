package org.example.presentation.view.frames.Patient;

import org.example.model.Patient;
import org.example.presentation.controller.PatientController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;
import org.example.utils.ConvertArray;
import org.example.utils.JPanelContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class Patients extends JPanelContainer {
    private final PatientController patientController;
    private final String[] columns;
    private final ActionListener addAction;
    private final AppLayout appLayout;
    private JTextField searchField;
    private List<Patient> allPatients;

    public Patients(Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add) {
        super(data, appLayout, addBtnLabel, colNames, add);
        this.patientController = new PatientController();
        this.columns = colNames;
        this.addAction = add;
        this.appLayout = appLayout;
        this.allPatients = patientController.displayPatients();

        // Create search panel above the table
        JPanel searchPanel = createSearchPanel();
        add(searchPanel, BorderLayout.NORTH);
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));

        JLabel searchLabel = new JLabel("Search by CIN:");
        searchLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        searchField = new JTextField(20);
        searchField.setPreferredSize(new Dimension(200, 30));

        Button searchButton = new Button("Search");
        searchButton.setPreferredSize(new Dimension(100, 30));
        searchButton.addActionListener(e -> performSearch());

        searchPanel.add(searchLabel);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private void performSearch() {
        String searchCIN = searchField.getText().trim().toLowerCase();

        List<Patient> filteredPatients;
        if (searchCIN.isEmpty()) {
            filteredPatients = allPatients;
        } else {
            filteredPatients = allPatients.stream()
                    .filter(patient -> patient.getCIN().toLowerCase().contains(searchCIN))
                    .collect(Collectors.toList());
        }

        Object[][] filteredData = ConvertArray.convertTo2DArray(
                filteredPatients,
                patient -> List.of(
                        patient.getId(),
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getPhone(),
                        patient.getCIN()
                )
        );

        dataTable.setModel(new Table(
                filteredData,
                columns,
                id -> onEdit(id, appLayout),
                id -> onDelete(id, appLayout),
                this::onShow
        ).getModel());

        dataTable.getColumn("Actions").setCellRenderer(new Table.ButtonPanelRenderer());
    }

    @Override
    protected void onEdit(Long id, AppLayout appLayout) {
        new EditPatient(id, appLayout);
    }

    @Override
    protected void onDelete(Long id, AppLayout appLayout) {
        new DeletePatient(id, appLayout);
    }

    @Override
    protected void onShow(Long id) {
        new ShowPatient(id);
    }
}