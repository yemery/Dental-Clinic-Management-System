package org.example.presentation.view.frames.MedicalCases;

import org.example.model.*;
import org.example.presentation.controller.*;
import org.example.presentation.records.PatientDisplay;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class AddMedicalCase extends Frame {
    private JComboBox<PatientDisplay> patientComboBox;
    private JList<Long> appointmentsList;
    private JList<Long> medicalHistoriesList;

    private AppLayout appLayout;

    public AddMedicalCase(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Controllers for fetching data
        PatientController patientController = new PatientController();
        AppointmentController appointmentController = new AppointmentController();
        MedicsHistoryController medicsHistoryController = new MedicsHistoryController();

        // Fetch data
        List<Patient> patients = patientController.displayPatients();
        List<Appointment> appointments = appointmentController.displayAppointments();
        List<MedicalHistory> medicalHistories = medicsHistoryController.showAllMHistories();

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        JLabel titleLabel = new JLabel("Add New Medical Case");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        panel.add(titleLabel);

        // Patient ComboBox with improved styling
        JLabel patientLabel = new JLabel("Select Patient:");
        patientLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(patientLabel);

        // Create PatientDisplay records array
        PatientDisplay[] patientDisplays = patients.stream()
                .map(patient -> new PatientDisplay(patient.getId(), patient.getFullName()))
                .toArray(PatientDisplay[]::new);

        patientComboBox = new JComboBox<>(patientDisplays);
        patientComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set preferred size for better height
        Dimension comboBoxSize = new Dimension(200, 30);
        patientComboBox.setPreferredSize(comboBoxSize);
        patientComboBox.setMaximumSize(comboBoxSize);

        panel.add(patientComboBox);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Appointments List
        JLabel appointmentsLabel = new JLabel("Select Appointments:");
        appointmentsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(appointmentsLabel);

        appointmentsList = new JList<>(
                appointments.stream().map(Appointment::getId).toArray(Long[]::new)
        );
        appointmentsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        appointmentsList.setVisibleRowCount(5);
        JScrollPane appointmentsScrollPane = new JScrollPane(appointmentsList);
        panel.add(appointmentsScrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        // Medical Histories List
        JLabel medicalHistoriesLabel = new JLabel("Select Medical Histories:");
        medicalHistoriesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(medicalHistoriesLabel);

        medicalHistoriesList = new JList<>(
                medicalHistories.stream().map(MedicalHistory::getId).toArray(Long[]::new)
        );
        medicalHistoriesList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        medicalHistoriesList.setVisibleRowCount(5);
        JScrollPane medicalHistoriesScrollPane = new JScrollPane(medicalHistoriesList);
        panel.add(medicalHistoriesScrollPane);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Submit Button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.addActionListener(e -> saveMedicalCase());
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);
        this.setVisible(true);
    }

    private void saveMedicalCase() {
        try {
            PatientDisplay selectedPatient = (PatientDisplay) patientComboBox.getSelectedItem();
            if (selectedPatient == null) {
                throw new IllegalArgumentException("A patient must be selected.");
            }

            Long selectedPatientId = selectedPatient.id(); // Using record accessor
            List<Long> selectedAppointmentIds = appointmentsList.getSelectedValuesList();
            List<Long> selectedMedicalHistoryIds = medicalHistoriesList.getSelectedValuesList();

            if (selectedAppointmentIds.isEmpty()) {
                throw new IllegalArgumentException("At least one appointment must be selected.");
            }
            if (selectedMedicalHistoryIds.isEmpty()) {
                throw new IllegalArgumentException("At least one medical history must be selected.");
            }

            // Create and save the medical case
            MedicalCase medicalCase = new MedicalCase();
            medicalCase.setPatient(selectedPatientId);
            medicalCase.setCreationDate(LocalDate.now());
            medicalCase.setAppointments(selectedAppointmentIds);
            medicalCase.setMedicalHistories(selectedMedicalHistoryIds);

            MedicalCaseController medicalCaseController = new MedicalCaseController();
            medicalCaseController.addMedicalCase(medicalCase);

            JOptionPane.showMessageDialog(this, "Medical Case added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Medical Cases");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}