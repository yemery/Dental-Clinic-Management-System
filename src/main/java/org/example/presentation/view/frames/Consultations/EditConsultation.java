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
import java.util.stream.Collectors;

public class EditConsultation extends Frame {
    private JComboBox<ConsultationType> typeComboBox;
    private Input<String> noteInput = new Input<>("Note", String.class);
    private JList<Long> interventionsList;
    private JComboBox<Long> certificateComboBox;
    private JComboBox<Long> invoiceComboBox;
    private JComboBox<Long> prescriptionComboBox;

    private AppLayout appLayout;
    private Consultation consultation;

    public EditConsultation(Long consultationId, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        ConsultationController consultationController = new ConsultationController();
        this.consultation = consultationController.getConsultation(consultationId);

        if (consultation == null) {
            JOptionPane.showMessageDialog(this, "Consultation not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        // Fetch data from controllers
        InterventionController interventionController = new InterventionController();
        List<Intervention> allInterventions = interventionController.displayAllInterventions();
        List<Long> selectedInterventionsIds = consultation.getInterventions();
        List<Long> allInterventionIds = allInterventions.stream()
                .map(Intervention::getId)
                .toList();

        CertificateController certificateController = new CertificateController();
        List<Long> certificateIds = certificateController.displayAllCertificates()
                .stream()
                .map(Certificate::getId)
                .toList();

        InvoiceController invoiceController = new InvoiceController();
        List<Long> invoiceIds = invoiceController.displayAllInvoices()
                .stream()
                .map(Invoice::getId)
                .toList();

        PrescriptionController prescriptionController = new PrescriptionController();
        List<Long> prescriptionIds = prescriptionController.displayPrescriptions()
                .stream()
                .map(Prescription::getId)
                .toList();

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titleLabel = new JLabel("Edit Consultation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        panel.add(titleLabel);

        // Type combo box
        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(typeLabel);

        typeComboBox = new JComboBox<>(ConsultationType.values());
        typeComboBox.setSelectedItem(consultation.getType());
        typeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(typeComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Note input field
        noteInput.setAlignmentX(Component.CENTER_ALIGNMENT);
        noteInput.setValue(consultation.getNote());
        panel.add(noteInput);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Interventions list (multi-select)
        JLabel interventionsLabel = new JLabel("Select Interventions:");
        interventionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(interventionsLabel);

        interventionsList = new JList<>(allInterventionIds.toArray(new Long[0]));
        interventionsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        interventionsList.setSelectedIndices(
                selectedInterventionsIds.stream()
                        .map(allInterventionIds::indexOf)
                        .mapToInt(i -> i)
                        .toArray()
        );
        JScrollPane interventionsScrollPane = new JScrollPane(interventionsList);
        panel.add(interventionsScrollPane);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Certificate combo box
        JLabel certificateLabel = new JLabel("Select Certificate:");
        certificateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(certificateLabel);

        certificateComboBox = new JComboBox<>(certificateIds.toArray(new Long[0]));
        certificateComboBox.setSelectedItem(consultation.getCertificate());
        certificateComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(certificateComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Invoice combo box
        JLabel invoiceLabel = new JLabel("Select Invoice:");
        invoiceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(invoiceLabel);

        invoiceComboBox = new JComboBox<>(invoiceIds.toArray(new Long[0]));
        invoiceComboBox.setSelectedItem(consultation.getInvoice());
        invoiceComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(invoiceComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Prescription combo box
        JLabel prescriptionLabel = new JLabel("Select Prescription:");
        prescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(prescriptionLabel);

        prescriptionComboBox = new JComboBox<>(prescriptionIds.toArray(new Long[0]));
        prescriptionComboBox.setSelectedItem(consultation.getPrescription());
        prescriptionComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(prescriptionComboBox);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Submit button
        Button submitBtn = new Button("Save Changes");
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
            List<Long> selectedInterventionIds = interventionsList.getSelectedValuesList();
            Long selectedCertificateId = (Long) certificateComboBox.getSelectedItem();
            Long selectedInvoiceId = (Long) invoiceComboBox.getSelectedItem();
            Long selectedPrescriptionId = (Long) prescriptionComboBox.getSelectedItem();

            if (type == null || note == null) {
                throw new IllegalArgumentException("Type and note are required.");
            }

            // Update consultation
            consultation.setType(type);
            consultation.setNote(note);
            consultation.setInterventions(selectedInterventionIds);
            consultation.setCertificate(selectedCertificateId);
            consultation.setInvoice(selectedInvoiceId);
            consultation.setPrescription(selectedPrescriptionId);

            ConsultationController consultationController = new ConsultationController();
            consultationController.updateConsultation(consultation);

            JOptionPane.showMessageDialog(this, "Consultation updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Consultations");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
