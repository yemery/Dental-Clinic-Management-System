package org.example.presentation.view.frames.Consultations;

import org.example.model.*;
import org.example.model.enums.ConsultationType;
import org.example.presentation.controller.*;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddConsultation extends Frame {
    private JComboBox<ConsultationType> typeComboBox;
    private Input<String> noteInput = new Input<>("Note", String.class);
    private JList<Intervention> interventionsList; // Multi-select for interventions
    private JComboBox<Certificate> certificateComboBox;
    private JComboBox<Invoice> invoiceComboBox;
    private JComboBox<Prescription> prescriptionComboBox;

    private AppLayout appLayout;

    public AddConsultation(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Fetch data from controllers
        InterventionController interventionController = new InterventionController();
        List<Intervention> interventions = interventionController.displayAllInterventions();

        CertificateController certificateController = new CertificateController();
        List<Certificate> certificates = certificateController.displayAllCertificates();

        InvoiceController invoiceController = new InvoiceController();
        List<Invoice> invoices = invoiceController.displayAllInvoices();

        PrescriptionController prescriptionController = new PrescriptionController();
        List<Prescription> prescriptions = prescriptionController.displayPrescriptions();

        // Setup UI
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        JLabel titleLabel = new JLabel("Add New Consultation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Type combo box
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(typeLabel);

        Dimension comboBoxSize = new Dimension(300, 30);

        typeComboBox = new JComboBox<>(ConsultationType.values());
        typeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        typeComboBox.setPreferredSize(comboBoxSize);
        panel.add(typeComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Note input field
        noteInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(noteInput);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Interventions list (multi-select)
        JLabel interventionsLabel = new JLabel("Select Interventions:");
        interventionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(interventionsLabel);

        interventionsList = new JList<>(interventions.toArray(new Intervention[0]));
        interventionsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        interventionsList.setVisibleRowCount(5);
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
        JScrollPane interventionsScrollPane = new JScrollPane(interventionsList);
        panel.add(interventionsScrollPane);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Certificate combo box
        JLabel certificateLabel = new JLabel("Select Certificate:");
        certificateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(certificateLabel);

        certificateComboBox = new JComboBox<>(certificates.toArray(new Certificate[0]));
        certificateComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Certificate) {
                    renderer.setText(String.valueOf(((Certificate) value).getId()));
                }
                return renderer;
            }
        });
        certificateComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        certificateComboBox.setPreferredSize(comboBoxSize);
        panel.add(certificateComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Invoice combo box
        JLabel invoiceLabel = new JLabel("Select Invoice:");
        invoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(invoiceLabel);

        invoiceComboBox = new JComboBox<>(invoices.toArray(new Invoice[0]));
        invoiceComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Invoice) {
                    renderer.setText(String.valueOf(((Invoice) value).getId()));
                }
                return renderer;
            }
        });
        invoiceComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        invoiceComboBox.setPreferredSize(comboBoxSize);
        panel.add(invoiceComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Prescription combo box
        JLabel prescriptionLabel = new JLabel("Select Prescription:");
        prescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(prescriptionLabel);

        prescriptionComboBox = new JComboBox<>(prescriptions.toArray(new Prescription[0]));
        prescriptionComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Prescription) {
                    renderer.setText(String.valueOf(((Prescription) value).getId()));
                }
                return renderer;
            }
        });
        prescriptionComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(prescriptionComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.addActionListener(e -> saveConsultation());
        panel.add(submitBtn);

        this.add(panel);
        this.setVisible(true);
    }

    private void saveConsultation() {
        try {
            ConsultationType type = (ConsultationType) typeComboBox.getSelectedItem();
            String note = noteInput.getValue();
            List<Intervention> selectedInterventions = interventionsList.getSelectedValuesList();
            Certificate selectedCertificate = (Certificate) certificateComboBox.getSelectedItem();
            Invoice selectedInvoice = (Invoice) invoiceComboBox.getSelectedItem();
            Prescription selectedPrescription = (Prescription) prescriptionComboBox.getSelectedItem();

            if (type == null || note == null) {
                throw new IllegalArgumentException("Type and note are required.");
            }

            // Create and save consultation
            Consultation consultation = new Consultation();
            consultation.setType(type);
            consultation.setNote(note);
            consultation.setInterventions(selectedInterventions.stream().map(Intervention::getId).toList());
            consultation.setCertificate(selectedCertificate != null ? selectedCertificate.getId() : null);
            consultation.setInvoice(selectedInvoice != null ? selectedInvoice.getId() : null);
            consultation.setPrescription(selectedPrescription != null ? selectedPrescription.getId() : null);

            ConsultationController consultationController = new ConsultationController();
            consultationController.addConsultation(consultation);

            JOptionPane.showMessageDialog(this, "Consultation saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Consultations");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
