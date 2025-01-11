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

public class AddPatient extends Frame {
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

    public AddPatient(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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
        saveButton.addActionListener(e -> savePatient());
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

    private void savePatient() {
        try {
            String firstNameValue = Objects.requireNonNullElse(firstName.getValue(), "");
            String lastNameValue = Objects.requireNonNullElse(lastName.getValue(), "");
            String emailValue = Objects.requireNonNullElse(email.getValue(), "");
            String cinValue = Objects.requireNonNullElse(cin.getValue(), "");
            String phoneValue = Objects.requireNonNullElse(phone.getValue(), "");
            String addressValue = Objects.requireNonNullElse(address.getValue(), "");
            String jobValue = Objects.requireNonNullElse(job.getValue(), "");
            String registrationValue = Objects.requireNonNullElse(registration.getValue(), "");
            LocalDate birthDateValue = Objects.requireNonNullElse(birthDate.getValue(), LocalDate.now());
            Gender genderValue = Objects.requireNonNullElse((Gender) gender.getSelectedItem(), null);
            Mutuelle mutuelleValue = Objects.requireNonNullElse((Mutuelle) mutuelleJComboBox.getSelectedItem(), null);

            validateRequiredFields(firstNameValue, lastNameValue, emailValue, cinValue,
                    birthDateValue, phoneValue, genderValue, registrationValue);
            Patient patient = new Patient(
                    firstNameValue,
                    lastNameValue,
                    cinValue,
                    birthDateValue,
                    addressValue,
                    phoneValue,
                    emailValue,
                    genderValue,
                    registrationValue,
                    mutuelleValue,
                    jobValue
            );

            PatientController patientController = new PatientController();
            patientController.addPatient(patient);

            JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Patients");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateRequiredFields(
            String firstNameValue,
            String lastNameValue,
            String emailValue,
            String cinValue,
            LocalDate birthDateValue,
            String phoneValue,
            Gender genderValue,
            String registrationValue) {

        if (isNullOrEmpty(firstNameValue)) {
            throw new IllegalArgumentException("First Name is a required field.");
        }
        if (isNullOrEmpty(lastNameValue)) {
            throw new IllegalArgumentException("Last Name is a required field.");
        }
        if (isNullOrEmpty(emailValue)) {
            throw new IllegalArgumentException("Email is a required field.");
        }
        if (isNullOrEmpty(cinValue)) {
            throw new IllegalArgumentException("CIN is a required field.");
        }
        if (birthDateValue == null) {
            throw new IllegalArgumentException("Birth Date is a required field.");
        }
        if (isNullOrEmpty(phoneValue)) {
            throw new IllegalArgumentException("Phone is a required field.");
        }
        if (genderValue == null) {
            throw new IllegalArgumentException("Gender is a required field.");
        }
        if (isNullOrEmpty(registrationValue)) {
            throw new IllegalArgumentException("Registration is a required field.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

}
