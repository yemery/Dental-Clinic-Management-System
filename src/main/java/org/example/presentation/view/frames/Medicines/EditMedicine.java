package org.example.presentation.view.frames.Medicines;

import org.example.model.Medicine;
import org.example.presentation.controller.MedicineController;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditMedicine extends Frame {
    private AppLayout appLayout;
    private Medicine medicine;
    private MedicineController controller;

    public EditMedicine(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new MedicineController();

        this.medicine = controller.getMedicine(id);

        if (medicine == null) {
            JOptionPane.showMessageDialog(this, "Medicine not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Name field
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(medicine.getName());

        // Price field
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(medicine.getPrice().toString());

        // Description field
        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField(medicine.getDescription());

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                String nameValue = nameField.getText();
                if (nameValue == null || nameValue.isEmpty()) {
                    throw new IllegalArgumentException("Name is required.");
                }

                Double priceValue = Double.parseDouble(priceField.getText());
                if (priceValue <= 0) {
                    throw new IllegalArgumentException("Price must be a positive value.");
                }

                String descriptionValue = descriptionField.getText();
                if (descriptionValue == null || descriptionValue.isEmpty()) {
                    throw new IllegalArgumentException("Description is required.");
                }

                medicine.setName(nameValue);
                medicine.setPrice(priceValue);
                medicine.setDescription(descriptionValue);

                controller.updateMedicine(medicine);
                JOptionPane.showMessageDialog(this, "Medicine updated successfully!");
                this.dispose();
                appLayout.getNavbar().simulateTabClick("Medicines");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating medicine: " + ex.getMessage());
            }
        });

        // Add components to panel
        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(priceLabel);
        mainPanel.add(priceField);
        mainPanel.add(descriptionLabel);
        mainPanel.add(descriptionField);
        mainPanel.add(new JLabel()); // Spacing
        mainPanel.add(saveButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        appLayout.getNavbar().simulateTabClick("Medicines");
    }
}
