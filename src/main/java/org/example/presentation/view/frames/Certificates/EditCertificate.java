package org.example.presentation.view.frames.Certificates;

import org.example.model.Certificate;
import org.example.presentation.controller.CertificateController;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;

public class EditCertificate extends Frame {
    private AppLayout appLayout;
    private Certificate certificate;
    private CertificateController controller;

    public EditCertificate(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new CertificateController();

        this.certificate = controller.getCertificateById(id);

        if (certificate == null) {
            JOptionPane.showMessageDialog(this, "Certificate not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Reason field
        JLabel reasonLabel = new JLabel("Reason:");
        JTextField reasonField = new JTextField(certificate.getReason());

        // Start Date field
        JLabel startDateLabel = new JLabel("Start Date:");
        JTextField startDateField = new JTextField(certificate.getStartDate().toString());

        // End Date field
        JLabel endDateLabel = new JLabel("End Date:");
        JTextField endDateField = new JTextField(certificate.getEndDate().toString());

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                String reasonValue = reasonField.getText();
                if (reasonValue == null || reasonValue.isEmpty()) {
                    throw new IllegalArgumentException("Reason is required.");
                }

                LocalDate startDateValue = LocalDate.parse(startDateField.getText());
                LocalDate endDateValue = LocalDate.parse(endDateField.getText());
                if (startDateValue.isAfter(endDateValue)) {
                    throw new IllegalArgumentException("Start date cannot be after end date.");
                }

                certificate.setReason(reasonValue);
                certificate.setStartDate(startDateValue);
                certificate.setEndDate(endDateValue);

                controller.updateCertificate(certificate);
                JOptionPane.showMessageDialog(this, "Certificate updated successfully!");
                this.dispose();
                appLayout.getNavbar().simulateTabClick("Certificates");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating certificate: " + ex.getMessage());
            }
        });

        // Add components to panel
        mainPanel.add(reasonLabel);
        mainPanel.add(reasonField);
        mainPanel.add(startDateLabel);
        mainPanel.add(startDateField);
        mainPanel.add(endDateLabel);
        mainPanel.add(endDateField);
        mainPanel.add(new JLabel()); // Spacing
        mainPanel.add(saveButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
