package org.example.presentation.view.frames.Prescriptions;

import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.PrescriptionController;
import org.example.presentation.controller.PrescriptionMedicineController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EditPrescription extends Frame {
    private JComboBox<PrescriptionMedicine> currentMedicineComboBox;
    private JComboBox<PrescriptionMedicine> availableMedicineComboBox;
    private JButton saveButton;
    private JButton removeMedicineButton;
    private JButton addMedicineButton;
    private AppLayout appLayout;

    private Prescription prescription;

    public EditPrescription(Long prescriptionId, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        PrescriptionController prescriptionController = new PrescriptionController();
        PrescriptionMedicineController prescriptionMedicineController = new PrescriptionMedicineController();

        this.prescription = prescriptionController.getPrescription(prescriptionId);

        if (prescription == null) {
            JOptionPane.showMessageDialog(this, "Prescription not found!");
            this.dispose();
            return;
        }

        // Fetch all prescription medicines and categorize them
        List<PrescriptionMedicine> currentMedicines = prescription.getPrescriptionsMedicine().stream()
                .map(prescriptionMedicineController::getPrescriptionMedicine)
                .filter(pm -> pm != null)
                .collect(Collectors.toList());

        List<PrescriptionMedicine> allMedicines = prescriptionMedicineController.displayAllPrescriptionMedicine();
        List<PrescriptionMedicine> availableMedicines = allMedicines.stream()
                .filter(pm -> currentMedicines.stream().noneMatch(cpm -> cpm.getId().equals(pm.getId())))
                .collect(Collectors.toList());

        // Frame setup
        setTitle("Edit Prescription");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
        gbc.insets = new Insets(8, 0, 8, 0); // Add 8px gap between rows

        // Current medicines label and ComboBox
        JLabel currentMedicinesLabel = new JLabel("Current Prescription Medicines:");
        currentMedicinesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        contentPanel.add(currentMedicinesLabel, gbc);

        currentMedicineComboBox = new JComboBox<>(currentMedicines.toArray(new PrescriptionMedicine[0]));
        currentMedicineComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PrescriptionMedicine) {
                    renderer.setText("ID: " + ((PrescriptionMedicine) value).getId());
                }
                return renderer;
            }
        });
        gbc.gridy++;
        contentPanel.add(currentMedicineComboBox, gbc);

        // Remove medicine button
        removeMedicineButton = new Button("Remove Prescription Medicine");
        removeMedicineButton.addActionListener(e -> removeMedicine());
        gbc.gridy++;
        contentPanel.add(removeMedicineButton, gbc);

        // Available medicines label and ComboBox
        JLabel availableMedicinesLabel = new JLabel("Available Prescription Medicines:");
        availableMedicinesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy++;
        contentPanel.add(availableMedicinesLabel, gbc);

        availableMedicineComboBox = new JComboBox<>(availableMedicines.toArray(new PrescriptionMedicine[0]));
        availableMedicineComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof PrescriptionMedicine) {
                    renderer.setText("ID: " + ((PrescriptionMedicine) value).getId());
                }
                return renderer;
            }
        });
        gbc.gridy++;
        contentPanel.add(availableMedicineComboBox, gbc);

        // Add medicine button
        addMedicineButton = new Button("Add Prescription Medicine");
        addMedicineButton.addActionListener(e -> addMedicine());
        gbc.gridy++;
        contentPanel.add(addMedicineButton, gbc);

        // Save button
        saveButton = new Button("Save Changes");
        saveButton.addActionListener(e -> savePrescription());
        gbc.gridy++;
        contentPanel.add(saveButton, gbc);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private void removeMedicine() {
        PrescriptionMedicine selectedMedicine = (PrescriptionMedicine) currentMedicineComboBox.getSelectedItem();
        if (selectedMedicine == null) {
            JOptionPane.showMessageDialog(this, "Please select a prescription medicine to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentMedicineComboBox.removeItem(selectedMedicine);
        availableMedicineComboBox.addItem(selectedMedicine);
    }

    private void addMedicine() {
        PrescriptionMedicine selectedMedicine = (PrescriptionMedicine) availableMedicineComboBox.getSelectedItem();
        if (selectedMedicine == null) {
            JOptionPane.showMessageDialog(this, "Please select a prescription medicine to add.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        availableMedicineComboBox.removeItem(selectedMedicine);
        currentMedicineComboBox.addItem(selectedMedicine);
    }

    private void savePrescription() {
        try {
            List<Long> updatedMedicinesIds = new ArrayList<>();
            for (int i = 0; i < currentMedicineComboBox.getItemCount(); i++) {
                updatedMedicinesIds.add(((PrescriptionMedicine) currentMedicineComboBox.getItemAt(i)).getId());
            }

            prescription.setPrescriptionsMedicine(updatedMedicinesIds);

            PrescriptionController prescriptionController = new PrescriptionController();
            prescriptionController.updatePrescription(prescription);

            JOptionPane.showMessageDialog(this, "Prescription updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Prescriptions");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
