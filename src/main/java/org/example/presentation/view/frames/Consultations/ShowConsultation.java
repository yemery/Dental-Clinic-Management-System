package org.example.presentation.view.frames.Consultations;

import org.example.model.*;
import org.example.presentation.controller.*;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class ShowConsultation extends Frame {
    public ShowConsultation(Long consultationId) {
        super();

        ConsultationController consultationController = new ConsultationController();
        Consultation consultation = consultationController.getConsultation(consultationId);

        if (consultation == null) {
            JOptionPane.showMessageDialog(this, "Consultation not found!");
            this.dispose();
            return;
        }

        // Fetch related data
        InterventionController interventionController = new InterventionController();
        CertificateController certificateController = new CertificateController();
        InvoiceController invoiceController = new InvoiceController();
        PrescriptionController prescriptionController = new PrescriptionController();

        List<Intervention> interventions = consultation.getInterventions() != null
                ? consultation.getInterventions().stream()
                .map(interventionController::getIntervention)
                .toList()
                : List.of();

        Certificate certificate = consultation.getCertificate() != null
                ? certificateController.getCertificateById(consultation.getCertificate())
                : null;

        Invoice invoice = consultation.getInvoice() != null
                ? invoiceController.getInvoice(consultation.getInvoice())
                : null;

        Prescription prescription = consultation.getPrescription() != null
                ? prescriptionController.getPrescription(consultation.getPrescription())
                : null;

        // Frame setup
        setTitle("Consultation Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Consultation details panel
        JPanel consultationDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        consultationDetailsPanel.setBorder(BorderFactory.createTitledBorder("Consultation Details"));

        consultationDetailsPanel.add(new JLabel("ID:"));
        consultationDetailsPanel.add(new JLabel(String.valueOf(consultation.getId())));

        consultationDetailsPanel.add(new JLabel("Type:"));
        consultationDetailsPanel.add(new JLabel(String.valueOf(consultation.getType())));

        consultationDetailsPanel.add(new JLabel("Note:"));
        consultationDetailsPanel.add(new JLabel(consultation.getNote()));

        consultationDetailsPanel.add(new JLabel("Date:"));
        consultationDetailsPanel.add(new JLabel(consultation.getDate().toString()));

        mainPanel.add(consultationDetailsPanel);

        // Interventions details panel
        JPanel interventionsPanel = new JPanel(new BorderLayout());
        interventionsPanel.setBorder(BorderFactory.createTitledBorder("Interventions"));

        JList<Intervention> interventionsList = new JList<>(interventions.toArray(new Intervention[0]));
        interventionsList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Intervention) {
                    renderer.setText("Intervention ID: " + ((Intervention) value).getId());
                }
                return renderer;
            }
        });
        interventionsPanel.add(new JScrollPane(interventionsList), BorderLayout.CENTER);
        mainPanel.add(interventionsPanel);

        // Certificate details
        if (certificate != null) {
            JPanel certificatePanel = new JPanel(new GridLayout(2, 2, 10, 10));
            certificatePanel.setBorder(BorderFactory.createTitledBorder("Certificate"));

            certificatePanel.add(new JLabel("ID:"));
            certificatePanel.add(new JLabel(String.valueOf(certificate.getId())));

            certificatePanel.add(new JLabel("Reason:"));
            certificatePanel.add(new JLabel(certificate.getReason()));

            mainPanel.add(certificatePanel);
        }

        // Invoice details
        if (invoice != null) {
            JPanel invoicePanel = new JPanel(new GridLayout(3, 2, 10, 10));
            invoicePanel.setBorder(BorderFactory.createTitledBorder("Invoice"));

            invoicePanel.add(new JLabel("ID:"));
            invoicePanel.add(new JLabel(String.valueOf(invoice.getId())));

            invoicePanel.add(new JLabel("Total Amount:"));
            invoicePanel.add(new JLabel(String.valueOf(invoice.getTotalAmount())));

            invoicePanel.add(new JLabel("Payed Amount:"));
            invoicePanel.add(new JLabel(String.valueOf(invoice.getPayedAmount())));

            mainPanel.add(invoicePanel);
        }

        // Prescription details
        if (prescription != null) {
            JPanel prescriptionPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            prescriptionPanel.setBorder(BorderFactory.createTitledBorder("Prescription"));

            prescriptionPanel.add(new JLabel("ID:"));
            prescriptionPanel.add(new JLabel(String.valueOf(prescription.getId())));

//            prescriptionPanel.add(new JLabel("Description:"));
//            prescriptionPanel.add(new JLabel(prescription.()));

            mainPanel.add(prescriptionPanel);
        }

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
