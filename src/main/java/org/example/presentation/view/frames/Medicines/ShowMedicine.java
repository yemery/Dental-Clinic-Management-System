package org.example.presentation.view.frames.Medicines;

import org.example.model.Medicine;
import org.example.presentation.controller.MedicineController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowMedicine extends Frame {
    public ShowMedicine(Long medicineId) {
        super();

        MedicineController controller = new MedicineController();
        Medicine medicine = controller.getMedicine(medicineId);

        if (medicine == null) {
            JOptionPane.showMessageDialog(this, "Medicine not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Medicine Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Medicine details panel
        JPanel medicineDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        medicineDetailsPanel.setBorder(BorderFactory.createTitledBorder("Medicine Details"));

        medicineDetailsPanel.add(new JLabel("ID:"));
        medicineDetailsPanel.add(new JLabel(String.valueOf(medicine.getId())));

        medicineDetailsPanel.add(new JLabel("Name:"));
        medicineDetailsPanel.add(new JLabel(medicine.getName()));

        medicineDetailsPanel.add(new JLabel("Price:"));
        medicineDetailsPanel.add(new JLabel(String.valueOf(medicine.getPrice())));

        medicineDetailsPanel.add(new JLabel("Description:"));
        medicineDetailsPanel.add(new JLabel(medicine.getDescription()));

        mainPanel.add(medicineDetailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
