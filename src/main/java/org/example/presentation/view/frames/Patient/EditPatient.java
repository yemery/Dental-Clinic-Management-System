package org.example.presentation.view.frames.Patient;

import org.example.model.enums.Gender;
import org.example.model.enums.Mutuelle;
import org.example.model.Patient;
import org.example.presentation.controller.PatientController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Objects;

public class EditPatient extends Frame {
    private Input<String> firstName = new Input<>("First Name", String.class);
    private Input<String> lastName = new Input<>("Last Name", String.class);
    private Input<String> email = new Input<>("Email", String.class);
    private Input<String> cin = new Input<>("CIN", String.class);
    private Input<String> phone = new Input<>("Phone", String.class);
    private Input<String> address = new Input<>("Address", String.class);
    private Input<String> job = new Input<>("Job", String.class);
    private Input<String> registration = new Input<>("Registration", String.class);
    private Input<LocalDate> birthDate = new Input<>("Birth Date", LocalDate.class);

    private JComboBox<Gender> gender = new JComboBox<>(Gender.values());
    private JComboBox<Mutuelle> mutuelleJComboBox = new JComboBox<>(Mutuelle.values());
    private JButton saveButton;

    private AppLayout appLayout;
    private Patient editablePatient;

    public EditPatient(Long patientId, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        PatientController patientController = new PatientController();
        this.editablePatient = patientController.getPatient(patientId);

        this.firstName.setValue(editablePatient.getFirstName());
        this.lastName.setValue(editablePatient.getLastName());
        this.email.setValue(editablePatient.getEmail());
        this.cin.setValue(editablePatient.getCIN());
        this.phone.setValue(editablePatient.getPhone());
        this.address.setValue(editablePatient.getAddress());
        this.job.setValue(editablePatient.getJob());
        this.registration.setValue(editablePatient.getRegistration());
        this.birthDate.setValue(editablePatient.getBirthDate());
        this.gender.setSelectedItem(editablePatient.getGender());
        this.mutuelleJComboBox.setSelectedItem(editablePatient.getMutuelle());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // adding 2 inputs per row
        int row = 0;
        addInputField(contentPanel, gbc, firstName, 0, row);
        addInputField(contentPanel, gbc, lastName, 1, row++);
        addInputField(contentPanel, gbc, email, 0, row);
        addInputField(contentPanel, gbc, cin, 1, row++);
        addInputField(contentPanel, gbc, phone, 0, row);
        addInputField(contentPanel, gbc, address, 1, row++);
        addInputField(contentPanel, gbc, job, 0, row);
        addInputField(contentPanel, gbc, registration, 1, row++);
        addInputField(contentPanel, gbc, birthDate, 0, row);
        addDropdownField(contentPanel, gbc, gender, 1, row++);
        addDropdownField(contentPanel, gbc, mutuelleJComboBox, 0, row++);

        saveButton = new Button("Save");
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(e -> updatePatient());
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(saveButton, gbc);

        add(contentPanel);
        setVisible(true);
    }

    private void addInputField(JPanel panel, GridBagConstraints gbc, Input<?> inputField, int col, int row) {
        gbc.gridx = col;
        gbc.gridy = row;

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.add(inputField, BorderLayout.CENTER);
        panel.add(fieldPanel, gbc);
    }

    private void addDropdownField(JPanel panel, GridBagConstraints gbc, JComboBox<?> comboBox, int col, int row) {
        gbc.gridx = col;
        gbc.gridy = row;

        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.add(comboBox, BorderLayout.CENTER);
        panel.add(fieldPanel, gbc);
    }

    private void updatePatient() {
        try {
            editablePatient.setFirstName(Objects.requireNonNullElse(firstName.getValue(), ""));
            editablePatient.setLastName(Objects.requireNonNullElse(lastName.getValue(), ""));
            editablePatient.setEmail(Objects.requireNonNullElse(email.getValue(), ""));
            editablePatient.setCIN(Objects.requireNonNullElse(cin.getValue(), ""));
            editablePatient.setPhone(Objects.requireNonNullElse(phone.getValue(), ""));
            editablePatient.setAddress(Objects.requireNonNullElse(address.getValue(), ""));
            editablePatient.setJob(Objects.requireNonNullElse(job.getValue(), ""));
            editablePatient.setRegistration(Objects.requireNonNullElse(registration.getValue(), ""));
            editablePatient.setBirthDate(Objects.requireNonNullElse(birthDate.getValue(), LocalDate.now()));
            editablePatient.setGender(Objects.requireNonNullElse((Gender) gender.getSelectedItem(), null));
            editablePatient.setMutuelle(Objects.requireNonNullElse((Mutuelle) mutuelleJComboBox.getSelectedItem(), null));

            validateRequiredFields(editablePatient);

            PatientController patientController = new PatientController();
            patientController.updatePatient(editablePatient);

            JOptionPane.showMessageDialog(this, "Patient updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Patients");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateRequiredFields(Patient patient) {
        if (isNullOrEmpty(patient.getFirstName())) {
            throw new IllegalArgumentException("First Name is a required field.");
        }
        if (isNullOrEmpty(patient.getLastName())) {
            throw new IllegalArgumentException("Last Name is a required field.");
        }
        if (isNullOrEmpty(patient.getEmail())) {
            throw new IllegalArgumentException("Email is a required field.");
        }
        if (isNullOrEmpty(patient.getCIN())) {
            throw new IllegalArgumentException("CIN is a required field.");
        }
        if (patient.getBirthDate() == null) {
            throw new IllegalArgumentException("Birth Date is a required field.");
        }
        if (isNullOrEmpty(patient.getPhone())) {
            throw new IllegalArgumentException("Phone is a required field.");
        }
        if (patient.getGender() == null) {
            throw new IllegalArgumentException("Gender is a required field.");
        }
        if (isNullOrEmpty(patient.getRegistration())) {
            throw new IllegalArgumentException("Registration is a required field.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
