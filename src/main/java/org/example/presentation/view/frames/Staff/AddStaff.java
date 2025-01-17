package org.example.presentation.view.frames.Staff;

import org.example.model.Staff;
import org.example.model.enums.Gender;
import org.example.model.enums.UserType;
import org.example.presentation.controller.StaffController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

public class AddStaff extends Frame {
    private Input<String> firstName = new Input<>("First Name", String.class);
    private Input<String> lastName = new Input<>("Last Name", String.class);
    private Input<LocalDate> birthDate = new Input<>("Birth Date", LocalDate.class);
    private Input<String> phone = new Input<>("Phone", String.class);
    private Input<String> email = new Input<>("Email", String.class);
    private Input<String> address = new Input<>("Address", String.class);
    private Input<String> cin = new Input<>("CIN", String.class);
    private Input<String> username = new Input<>("Username", String.class);
    private Input<String> password = new Input<>("Password", String.class);
    private Input<Double> salary = new Input<>("Salary", Double.class);

    private JComboBox<Gender> gender = new JComboBox<>(Gender.values());
    private JComboBox<UserType> userTypeComboBox = new JComboBox<>(Arrays.stream(UserType.values()).skip(1).toArray(UserType[]::new));
    private JButton saveButton;

    private AppLayout appLayout;

    public AddStaff(AppLayout appLayout) {
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
        addInputField(contentPanel, gbc, birthDate, 0, row);
        addInputField(contentPanel, gbc, phone, 1, row++);
        addInputField(contentPanel, gbc, email, 0, row);
        addInputField(contentPanel, gbc, cin, 1, row++);
        addInputField(contentPanel, gbc, address, 0, row);
        addInputField(contentPanel, gbc, salary, 1, row++);
        addInputField(contentPanel, gbc, username, 0, row);
        addInputField(contentPanel, gbc, password, 1, row++);
        addDropdownField(contentPanel, gbc, gender, 0, row++);
        addDropdownField(contentPanel, gbc, userTypeComboBox, 1, row++);

        saveButton = new Button("Save");
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(e -> saveStaff());
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

    private void saveStaff() {
        try {
            String firstNameValue = Objects.requireNonNullElse(firstName.getValue(), "");
            String lastNameValue = Objects.requireNonNullElse(lastName.getValue(), "");
            LocalDate birthDateValue = Objects.requireNonNullElse(birthDate.getValue(), LocalDate.now());
            String phoneValue = Objects.requireNonNullElse(phone.getValue(), "");
            String emailValue = Objects.requireNonNullElse(email.getValue(), "");
            String addressValue = Objects.requireNonNullElse(address.getValue(), "");
            String cinValue = Objects.requireNonNullElse(cin.getValue(), "");
            String usernameValue = Objects.requireNonNullElse(username.getValue(), "");
            String passwordValue = Objects.requireNonNullElse(password.getValue(), "");
            Double salaryValue = Objects.requireNonNullElse(salary.getValue(), 0.0);
            Gender genderValue = Objects.requireNonNullElse((Gender) gender.getSelectedItem(), null);
            UserType userTypeValue = Objects.requireNonNullElse((UserType) userTypeComboBox.getSelectedItem(), null);

            validateRequiredFields(firstNameValue, lastNameValue, usernameValue, passwordValue, genderValue, userTypeValue, salaryValue);

            Staff staff = new Staff(
                    firstNameValue,
                    lastNameValue,
                    cinValue,
                    birthDateValue,
                    addressValue,
                    phoneValue,
                    emailValue,
                    genderValue,
                    usernameValue,
                    passwordValue,
                    salaryValue,
                    userTypeValue
            );

            StaffController staffController = new StaffController();
            staffController.addUser(staff);

            JOptionPane.showMessageDialog(this, "Staff added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Staff");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateRequiredFields(
            String firstNameValue,
            String lastNameValue,
            String usernameValue,
            String passwordValue,
            Gender genderValue,
            UserType userTypeValue,
            Double salaryValue) {

        if (isNullOrEmpty(firstNameValue)) {
            throw new IllegalArgumentException("First Name is a required field.");
        }
        if (isNullOrEmpty(lastNameValue)) {
            throw new IllegalArgumentException("Last Name is a required field.");
        }
        if (isNullOrEmpty(usernameValue)) {
            throw new IllegalArgumentException("Username is a required field.");
        }
        if (isNullOrEmpty(passwordValue)) {
            throw new IllegalArgumentException("Password is a required field.");
        }
        if (genderValue == null) {
            throw new IllegalArgumentException("Gender is a required field.");
        }
        if (userTypeValue == null) {
            throw new IllegalArgumentException("User Type is a required field.");
        }
        if (salaryValue == null || salaryValue <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
