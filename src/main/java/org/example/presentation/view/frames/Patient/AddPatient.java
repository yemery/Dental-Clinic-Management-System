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

        // Frame setup
        setTitle("Add Patient");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600); // Adjust size to accommodate larger inputs
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Set preferred size for inputs
        Dimension inputSize = new Dimension(400, 40);

        // Add input fields with labels above them
        addInputField(contentPanel, gbc, firstName, inputSize);
        addInputField(contentPanel, gbc, lastName, inputSize);
        addInputField(contentPanel, gbc, email, inputSize);
        addInputField(contentPanel, gbc, cin, inputSize);
        addInputField(contentPanel, gbc, phone, inputSize);
        addInputField(contentPanel, gbc, address, inputSize);
        addInputField(contentPanel, gbc, job, inputSize);
        addInputField(contentPanel, gbc, registration, inputSize);
        addInputField(contentPanel, gbc, birthDate, inputSize);

        // Add dropdowns for gender and mutuelle
        addDropdownField(contentPanel, gbc, "Gender", gender, inputSize);
        addDropdownField(contentPanel, gbc, "Mutuelle", mutuelleJComboBox, inputSize);

        // Save button
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        saveButton = new Button("Save");
        saveButton.setPreferredSize(new Dimension(200, 50)); // Larger button for better UX
        saveButton.addActionListener(e -> savePatient());
        contentPanel.add(saveButton, gbc);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addInputField(JPanel panel, GridBagConstraints gbc, Input<?> inputField, Dimension size) {
        gbc.gridy++;
        inputField.setPreferredSize(size); // Apply larger size to the input
        panel.add(inputField, gbc);
    }

    private void addDropdownField(JPanel panel, GridBagConstraints gbc, String labelText, JComboBox<?> comboBox, Dimension size) {
        gbc.gridy++;
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label, gbc);

        gbc.gridy++;
        comboBox.setPreferredSize(size); // Apply larger size to the combo box
        panel.add(comboBox, gbc);
    }

    private void savePatient() {
        try {
            // Fetch input values
            String firstNameValue = firstName.getValue();
            String lastNameValue = lastName.getValue();
            String emailValue = email.getValue();
            String cinValue = cin.getValue();
            String phoneValue = phone.getValue();
            String addressValue = address.getValue();
            String jobValue = job.getValue();
            String registrationValue = registration.getValue();
            LocalDate birthDateValue = birthDate.getValue();
            Gender genderValue = (Gender) gender.getSelectedItem();
            Mutuelle mutuelleValue = (Mutuelle) mutuelleJComboBox.getSelectedItem();

            if (firstNameValue == null || lastNameValue == null || emailValue == null || cinValue == null) {
                throw new IllegalArgumentException("First Name, Last Name, Email, and CIN are required fields.");
            }

            // Save patient
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
}
