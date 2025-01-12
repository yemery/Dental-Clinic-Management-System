package org.example.presentation.view.frames.MedicalHistory;

import org.example.model.MedicalHistory;
import org.example.presentation.controller.MedicsHistoryController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowMedicalHistory extends Frame {
    public ShowMedicalHistory(Long medicalHistoryId) {
        super();

        MedicsHistoryController controller = new MedicsHistoryController();
        MedicalHistory medicalHistory = controller.getMh(medicalHistoryId);

        if (medicalHistory == null) {
            JOptionPane.showMessageDialog(this, "Medical History not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Medical History Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Medical History details panel
        JPanel detailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Medical History Details"));

        detailsPanel.add(new JLabel("ID:"));
        detailsPanel.add(new JLabel(String.valueOf(medicalHistory.getId())));

        detailsPanel.add(new JLabel("Label:"));
        detailsPanel.add(new JLabel(medicalHistory.getLabel()));

        detailsPanel.add(new JLabel("Category:"));
        detailsPanel.add(new JLabel(medicalHistory.getCategory().toString()));

        detailsPanel.add(new JLabel("Description:"));
        detailsPanel.add(new JLabel(medicalHistory.getDescription()));

        detailsPanel.add(new JLabel("Risk:"));
        detailsPanel.add(new JLabel(medicalHistory.getRisk().toString()));

        mainPanel.add(detailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
