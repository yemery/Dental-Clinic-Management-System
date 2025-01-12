package org.example.presentation.view.frames.PrescriptionsMedicines;

import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.PrescriptionMedicineController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowPM extends Frame {
    public ShowPM(Long prescriptionMedicineId) {
        super();

        PrescriptionMedicineController pmController = new PrescriptionMedicineController();
        PrescriptionMedicine prescriptionMedicine = pmController.getPrescriptionMedicine(prescriptionMedicineId);

        if (prescriptionMedicine == null) {
            JOptionPane.showMessageDialog(this, "Prescription Medicine not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Prescription Medicine Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Prescription Medicine details panel
        JPanel detailsPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Prescription Medicine Details"));

        detailsPanel.add(new JLabel("ID:"));
        detailsPanel.add(new JLabel(String.valueOf(prescriptionMedicine.getId())));

        detailsPanel.add(new JLabel("Medicine ID:"));
        detailsPanel.add(new JLabel(String.valueOf(prescriptionMedicine.getMedicine())));

        detailsPanel.add(new JLabel("Description:"));
        detailsPanel.add(new JLabel(prescriptionMedicine.getDescription()));

        detailsPanel.add(new JLabel("Minimum Quantity:"));
        detailsPanel.add(new JLabel(String.valueOf(prescriptionMedicine.getMin())));

        detailsPanel.add(new JLabel("Maximum Quantity:"));
        detailsPanel.add(new JLabel(String.valueOf(prescriptionMedicine.getMax())));

        mainPanel.add(detailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
