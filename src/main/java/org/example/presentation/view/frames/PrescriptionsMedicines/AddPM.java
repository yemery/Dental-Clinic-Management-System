package org.example.presentation.view.frames.PrescriptionsMedicines;

import org.example.model.Medicine;
import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.MedicineController;
import org.example.presentation.controller.PrescriptionMedicineController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddPM extends Frame {
    private JComboBox<Medicine> medicineComboBox; // ComboBox for selecting Medicine
    private Input<String> descriptionInput = new Input<>("Description", String.class);
    private Input<Integer> minInput = new Input<>("Minimum Quantity", Integer.class);
    private Input<Integer> maxInput = new Input<>("Maximum Quantity", Integer.class);

    private AppLayout appLayout;

    public AddPM(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Fetch available medicines from MedicineController
        MedicineController medicineController = new MedicineController();
        List<Medicine> medicines = medicineController.getAllMedicine();

        // Create ComboBox and populate it
        medicineComboBox = new JComboBox<>(medicines.toArray(new Medicine[0]));
        medicineComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Medicine) {
                    renderer.setText(((Medicine) value).getName());
                }
                return renderer;
            }
        });

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        // Title label
        JLabel titleLabel = new JLabel("Add New Prescription Medicine");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Medicine ComboBox
        JLabel medicineLabel = new JLabel("Select Medicine:");
        medicineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(medicineLabel);

        medicineComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        medicineComboBox.setPreferredSize(new Dimension(200, 30));
        panel.add(medicineComboBox);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs

        // Add other input fields
        descriptionInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        minInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        maxInput.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(descriptionInput);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(minInput);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(maxInput);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add spacing before the button

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Make the button text larger
        submitBtn.addActionListener(e -> savePrescriptionMedicine()); // Add submit logic
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);
        this.setVisible(true);
    }

    private void savePrescriptionMedicine() {
        try {
            Medicine selectedMedicine = (Medicine) medicineComboBox.getSelectedItem();
            String description = descriptionInput.getValue();
            Integer min = minInput.getValue();
            Integer max = maxInput.getValue();

            if (selectedMedicine == null || description == null || min == null || max == null) {
                throw new IllegalArgumentException("All fields are required.");
            }

            if (min > max) {
                throw new IllegalArgumentException("Minimum quantity cannot be greater than maximum quantity.");
            }

            // Create and save PrescriptionMedicine
            PrescriptionMedicineController pmController = new PrescriptionMedicineController();
            PrescriptionMedicine pm = new PrescriptionMedicine();
            pm.setMedicine(selectedMedicine.getId());
            pm.setDescription(description);
            pm.setMin(min);
            pm.setMax(max);

            pmController.addPrescriptionMedicine(pm);

            JOptionPane.showMessageDialog(this, "Prescription Medicine saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("PrescriptionMedicines");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
