package org.example.presentation.view.frames.Medicines;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;
import org.example.model.Medicine;
import org.example.presentation.controller.MedicineController;

import javax.swing.*;
import java.awt.*;

public class AddMedicine extends Frame {

    private Input price = new Input("Price", Double.class);
    private Input name = new Input("Name", String.class);
    private Input description = new Input("Description", String.class);

    private AppLayout appLayout;

    public AddMedicine(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Set the close operation for this frame only
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        // Title label
        JLabel titleLabel = new JLabel("Add New Medicine");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Add input fields
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        description.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(name);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(price);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(description);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add spacing before the button

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Make the button text larger
        submitBtn.addActionListener(e -> saveMedicine()); // Add submit logic
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);

        this.setVisible(true);
    }

    private void saveMedicine() {
        try {
            // Validate inputs
            String nameValue = (String) name.getValue();
            if (nameValue == null || nameValue.isEmpty()) {
                throw new IllegalArgumentException("Name is required.");
            }

            Double priceValue = (Double) price.getValue();
            if (priceValue == null || priceValue <= 0) {
                throw new IllegalArgumentException("Price must be a positive value.");
            }

            String descriptionValue = (String) description.getValue();
            if (descriptionValue == null || descriptionValue.isEmpty()) {
                throw new IllegalArgumentException("Description is required.");
            }

            // Create and save medicine
            MedicineController medicineController = new MedicineController();
            Medicine medicine = new Medicine(priceValue, nameValue, descriptionValue);
            medicineController.addMedicine(medicine);

            JOptionPane.showMessageDialog(this, "Medicine saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close the frame
            appLayout.getNavbar().simulateTabClick("Medicines");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
