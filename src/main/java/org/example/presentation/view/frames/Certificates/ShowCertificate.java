package org.example.presentation.view.frames.Certificates;

import org.example.model.Certificate;
import org.example.presentation.controller.CertificateController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowCertificate extends Frame {
    public ShowCertificate(Long certificateId) {
        super();

        CertificateController controller = new CertificateController();
        Certificate certificate = controller.getCertificateById(certificateId);

        // Frame setup
        setTitle("Certificate Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Certificate details panel
        JPanel certificateDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        certificateDetailsPanel.setBorder(BorderFactory.createTitledBorder("Certificate Details"));

        certificateDetailsPanel.add(new JLabel("ID:"));
        certificateDetailsPanel.add(new JLabel(String.valueOf(certificate.getId())));

        certificateDetailsPanel.add(new JLabel("Reason:"));
        certificateDetailsPanel.add(new JLabel(certificate.getReason()));

        certificateDetailsPanel.add(new JLabel("Start Date:"));
        certificateDetailsPanel.add(new JLabel(certificate.getStartDate().toString()));

        certificateDetailsPanel.add(new JLabel("End Date:"));
        certificateDetailsPanel.add(new JLabel(certificate.getEndDate().toString()));

        mainPanel.add(certificateDetailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
