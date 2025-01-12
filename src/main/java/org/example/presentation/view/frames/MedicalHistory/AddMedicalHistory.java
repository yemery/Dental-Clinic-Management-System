package org.example.presentation.view.frames.MedicalHistory;

import org.example.model.MedicalHistory;
import org.example.model.enums.CategoryMedicalHistory;
import org.example.model.enums.Risk;
import org.example.presentation.controller.MedicsHistoryController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;

public class AddMedicalHistory extends Frame {
    private Input<String> labelInput = new Input<>("Label", String.class);
    private JComboBox<CategoryMedicalHistory> categoryComboBox;
    private Input<String> descriptionInput = new Input<>("Description", String.class);
    private JComboBox<Risk> riskComboBox;

    private AppLayout appLayout;

    public AddMedicalHistory(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        // Title label
        JLabel titleLabel = new JLabel("Add New Medical History");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Add input fields
        labelInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Category combo box
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(categoryLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing before combo box

        categoryComboBox = new JComboBox<>(CategoryMedicalHistory.values());
        categoryComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(categoryComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing

        // Add label and description inputs
        panel.add(labelInput);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(descriptionInput);

        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing

        // Risk combo box
        JLabel riskLabel = new JLabel("Risk:");
        riskLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(riskLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing before combo box

        riskComboBox = new JComboBox<>(Risk.values());
        riskComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(riskComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add spacing before the button

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Make the button text larger
        submitBtn.addActionListener(e -> saveMedicalHistory()); // Add submit logic
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);
        this.setVisible(true);
    }

    private void saveMedicalHistory() {
        try {
            String label = labelInput.getValue();
            CategoryMedicalHistory category = (CategoryMedicalHistory) categoryComboBox.getSelectedItem();
            String description = descriptionInput.getValue();
            Risk risk = (Risk) riskComboBox.getSelectedItem();

            if (label == null || category == null || description == null || risk == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            // Create and save MedicalHistory
            MedicsHistoryController mhController = new MedicsHistoryController();
            MedicalHistory medicalHistory = new MedicalHistory();
            medicalHistory.setLabel(label);
            medicalHistory.setCategory(category);
            medicalHistory.setDescription(description);
            medicalHistory.setRisk(risk);

            mhController.addMHistory(medicalHistory);

            JOptionPane.showMessageDialog(this, "Medical History saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("MedicalHistories");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
