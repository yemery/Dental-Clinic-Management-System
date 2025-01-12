package org.example.presentation.view.frames.MedicalHistory;

import org.example.model.MedicalHistory;
import org.example.model.enums.CategoryMedicalHistory;
import org.example.model.enums.Risk;
import org.example.presentation.controller.MedicsHistoryController;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditMedicalHistory extends Frame {
    private AppLayout appLayout;
    private MedicalHistory medicalHistory;
    private MedicsHistoryController controller;

    public EditMedicalHistory(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new MedicsHistoryController();

        this.medicalHistory = controller.getMh(id);

        if (medicalHistory == null) {
            JOptionPane.showMessageDialog(this, "Medical History not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label field
        JLabel labelLabel = new JLabel("Label:");
        JTextField labelField = new JTextField(medicalHistory.getLabel());

        // Category combo box
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<CategoryMedicalHistory> categoryComboBox = new JComboBox<>(CategoryMedicalHistory.values());
        categoryComboBox.setSelectedItem(medicalHistory.getCategory());

        // Description field
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(medicalHistory.getDescription());

        // Risk combo box
        JLabel riskLabel = new JLabel("Risk:");
        JComboBox<Risk> riskComboBox = new JComboBox<>(Risk.values());
        riskComboBox.setSelectedItem(medicalHistory.getRisk());

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                String label = labelField.getText();
                CategoryMedicalHistory category = (CategoryMedicalHistory) categoryComboBox.getSelectedItem();
                String description = descriptionField.getText();
                Risk risk = (Risk) riskComboBox.getSelectedItem();

                if (label.isEmpty() || category == null || description.isEmpty() || risk == null) {
                    throw new IllegalArgumentException("All fields are required.");
                }

                medicalHistory.setLabel(label);
                medicalHistory.setCategory(category);
                medicalHistory.setDescription(description);
                medicalHistory.setRisk(risk);

                controller.updateMHistory(medicalHistory);

                JOptionPane.showMessageDialog(this, "Medical History updated successfully!");
                this.dispose();
                appLayout.getNavbar().simulateTabClick("MedicalHistories");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating Medical History: " + ex.getMessage());
            }
        });

        // Add components to panel
        mainPanel.add(labelLabel);
        mainPanel.add(labelField);
        mainPanel.add(categoryLabel);
        mainPanel.add(categoryComboBox);
        mainPanel.add(descriptionLabel);
        mainPanel.add(descriptionField);
        mainPanel.add(riskLabel);
        mainPanel.add(riskComboBox);
        mainPanel.add(new JLabel()); // Empty space
        mainPanel.add(saveButton);

        this.add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
    }
}
