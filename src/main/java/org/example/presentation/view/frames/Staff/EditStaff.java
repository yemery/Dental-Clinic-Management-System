package org.example.presentation.view.frames.Staff;

import org.example.model.enums.Gender;
import org.example.model.enums.UserType;
import org.example.model.Staff;
import org.example.presentation.controller.StaffController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Objects;

public class EditStaff extends Frame {
    private Input<String> firstName = new Input<>("First Name", String.class);
    private Input<String> lastName = new Input<>("Last Name", String.class);
    private Input<String> email = new Input<>("Email", String.class);
    private Input<String> cin = new Input<>("CIN", String.class);
    private Input<String> phone = new Input<>("Phone", String.class);
    private Input<String> address = new Input<>("Address", String.class);
    private Input<String> username = new Input<>("Username", String.class);
    private Input<Double> salary = new Input<>("Salary", Double.class);
    private JComboBox<Gender> gender = new JComboBox<>(Gender.values());
    private JComboBox<UserType> userTypeComboBox = new JComboBox<>(UserType.values());
    private JButton saveButton;

    private AppLayout appLayout;
    private Staff staff;

    public EditStaff(Long staffId, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        StaffController staffController = new StaffController();
        this.staff = staffController.getUser(staffId);

        if (staff == null) {
            JOptionPane.showMessageDialog(this, "Staff not found!", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Pre-fill fields with staff data
        firstName.setValue(staff.getFirstName());
        lastName.setValue(staff.getLastName());
        email.setValue(staff.getEmail());
        cin.setValue(staff.getCIN());
        phone.setValue(staff.getPhone());
        address.setValue(staff.getAddress());
        username.setValue(staff.getUsername());
        salary.setValue(staff.getSalary());
        gender.setSelectedItem(staff.getGender());
        userTypeComboBox.setSelectedItem(staff.getUserType());

        // Adding fields to panel
        int row = 0;
        addInputField(contentPanel, gbc, firstName, 0, row);
        addInputField(contentPanel, gbc, lastName, 1, row++);
        addInputField(contentPanel, gbc, email, 0, row);
        addInputField(contentPanel, gbc, cin, 1, row++);
        addInputField(contentPanel, gbc, phone, 0, row);
        addInputField(contentPanel, gbc, address, 1, row++);
        addInputField(contentPanel, gbc, username, 0, row);
        addInputField(contentPanel, gbc, salary, 1, row++);
        addDropdownField(contentPanel, gbc, gender, 0, row);
        addDropdownField(contentPanel, gbc, userTypeComboBox, 1, row++);

        saveButton = new Button("Save Changes");
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
            String emailValue = Objects.requireNonNullElse(email.getValue(), "");
            String cinValue = Objects.requireNonNullElse(cin.getValue(), "");
            String phoneValue = Objects.requireNonNullElse(phone.getValue(), "");
            String addressValue = Objects.requireNonNullElse(address.getValue(), "");
            String usernameValue = Objects.requireNonNullElse(username.getValue(), "");
            Double salaryValue = Objects.requireNonNullElse(salary.getValue(), 0.0);
            Gender genderValue = Objects.requireNonNullElse((Gender) gender.getSelectedItem(), null);
            UserType userTypeValue = Objects.requireNonNullElse((UserType) userTypeComboBox.getSelectedItem(), null);

            validateRequiredFields(firstNameValue, lastNameValue, emailValue, cinValue,
                    phoneValue, usernameValue, salaryValue, genderValue, userTypeValue);

            staff.setFirstName(firstNameValue);
            staff.setLastName(lastNameValue);
            staff.setEmail(emailValue);
            staff.setCIN(cinValue);
            staff.setPhone(phoneValue);
            staff.setAddress(addressValue);
            staff.setUsername(usernameValue);
            staff.setSalary(salaryValue);
            staff.setGender(genderValue);
            staff.setUserType(userTypeValue);

            StaffController staffController = new StaffController();
            staffController.updateUser(staff);

            JOptionPane.showMessageDialog(this, "Staff updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Staff");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateRequiredFields(
            String firstNameValue,
            String lastNameValue,
            String emailValue,
            String cinValue,
            String phoneValue,
            String usernameValue,
            Double salaryValue,
            Gender genderValue,
            UserType userTypeValue) {

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
        if (isNullOrEmpty(phoneValue)) {
            throw new IllegalArgumentException("Phone is a required field.");
        }
        if (isNullOrEmpty(usernameValue)) {
            throw new IllegalArgumentException("Username is a required field.");
        }
        if (salaryValue == null || salaryValue <= 0) {
            throw new IllegalArgumentException("Salary must be greater than zero.");
        }
        if (genderValue == null) {
            throw new IllegalArgumentException("Gender is a required field.");
        }
        if (userTypeValue == null) {
            throw new IllegalArgumentException("User Type is a required field.");
        }
    }

    private boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
