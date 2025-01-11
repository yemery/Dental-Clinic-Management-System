package org.example.presentation.view.frames.Certificates;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;
import org.example.model.Certificate;
import org.example.presentation.controller.CertificateController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddCertificate extends Frame {
    private Input reason = new Input("Reason", String.class);
    private Input<LocalDate> startDate = new Input("Start Date", LocalDate.class);
    private Input<LocalDate> endDate = new Input("End Date", LocalDate.class);

    private AppLayout appLayout;

    public AddCertificate(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        // Title label
        JLabel titleLabel = new JLabel("Add New Certificate");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Add input fields
        reason.setAlignmentX(Component.CENTER_ALIGNMENT);
        startDate.setAlignmentX(Component.CENTER_ALIGNMENT);
        endDate.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(reason);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(startDate);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(endDate);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add spacing before the button

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Make the button text larger
        submitBtn.addActionListener(e -> saveCertificate()); // Add submit logic
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);

        this.setVisible(true);
    }

    private void saveCertificate() {
        try {
            // Validate inputs
            String reasonValue = (String) reason.getValue();
            if (reasonValue == null || reasonValue.isEmpty()) {
                throw new IllegalArgumentException("Reason is required.");
            }

            LocalDate startDateValue = startDate.getValue();
            LocalDate endDateValue = endDate.getValue();
            if (startDateValue == null || endDateValue == null) {
                throw new IllegalArgumentException("Both start date and end date are required.");
            }

            if (startDateValue.isAfter(endDateValue)) {
                throw new IllegalArgumentException("Start date cannot be after end date.");
            }

            // Create and save certificate
            CertificateController certificateController = new CertificateController();
            Certificate certificate = new Certificate(reasonValue, startDateValue, endDateValue);
            certificateController.addNewCertificate(certificate);

            JOptionPane.showMessageDialog(this, "Certificate saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Certificates");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
