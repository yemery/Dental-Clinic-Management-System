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

public class EditPM extends Frame {
    private JComboBox<Medicine> medicineComboBox; // Use JComboBox for Medicine selection
    private Input<String> descriptionInput;
    private Input<Integer> minInput;
    private Input<Integer> maxInput;

    private AppLayout appLayout;
    private PrescriptionMedicine prescriptionMedicine;

    public EditPM(Long prescriptionMedicineId, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        PrescriptionMedicineController pmController = new PrescriptionMedicineController();
        this.prescriptionMedicine = pmController.getPrescriptionMedicine(prescriptionMedicineId);

        if (prescriptionMedicine == null) {
            JOptionPane.showMessageDialog(this, "Prescription Medicine not found!");
            this.dispose();
            return;
        }

        // Fetch medicines from MedicineController
        MedicineController medicineController = new MedicineController();
        List<Medicine> medicines = medicineController.getAllMedicine();

        // Initialize medicine combo box and set selected value
        medicineComboBox = new JComboBox<>(medicines.toArray(new Medicine[0]));
        medicineComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Medicine) {
                    renderer.setText(((Medicine) value).getName()); // Display Medicine name
                }
                return renderer;
            }
        });

        // Set selected medicine based on the existing value in prescriptionMedicine
        for (int i = 0; i < medicineComboBox.getItemCount(); i++) {
            Medicine medicine = medicineComboBox.getItemAt(i);
            if (medicine != null && medicine.getId().equals(prescriptionMedicine.getMedicine())) {
                medicineComboBox.setSelectedItem(medicine);
                break;
            }
        }

        // Initialize other input fields with existing values
        descriptionInput = new Input<>("Description", String.class);
        descriptionInput.setValue(prescriptionMedicine.getDescription());

        minInput = new Input<>("Minimum Quantity", Integer.class);
        minInput.setValue(prescriptionMedicine.getMin());

        maxInput = new Input<>("Maximum Quantity", Integer.class);
        maxInput.setValue(prescriptionMedicine.getMax());

        setupUI();
    }

    private void setupUI() {
        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        JLabel titleLabel = new JLabel("Edit Prescription Medicine");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

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
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(minInput);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(maxInput);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Submit button
        Button submitBtn = new Button("Save Changes");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.addActionListener(e -> savePrescriptionMedicine());
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

            // Update PrescriptionMedicine
            prescriptionMedicine.setMedicine(selectedMedicine.getId());
            prescriptionMedicine.setDescription(description);
            prescriptionMedicine.setMin(min);
            prescriptionMedicine.setMax(max);

            PrescriptionMedicineController pmController = new PrescriptionMedicineController();
            pmController.updatePrescriptionMedicine(prescriptionMedicine);

            JOptionPane.showMessageDialog(this, "Prescription Medicine updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("PrescriptionMedicines");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
