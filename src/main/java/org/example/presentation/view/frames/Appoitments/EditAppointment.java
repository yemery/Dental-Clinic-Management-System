package org.example.presentation.view.frames.Appoitments;

import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;
import org.example.model.Appointment;
import org.example.model.enums.AppointementStatus;
import org.example.model.enums.AppoitmentType;
import org.example.presentation.controller.AppointmentController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class EditAppointment extends Frame {
    private AppLayout appLayout;
    private Appointment appointment;
    private AppointmentController controller;

    public EditAppointment(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new AppointmentController();

        // Fetch the appointment by ID
        this.appointment = controller.getAppointment(id);

        if (appointment == null) {
            JOptionPane.showMessageDialog(this, "Appointment not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Time field
        JLabel timeLabel = new JLabel("Time:");
        JTextField timeField = new JTextField(appointment.getTime().toString());

        // Date field
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField(appointment.getDate().toString());

        // Type combo box
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<AppoitmentType> typeCombo = new JComboBox<>(AppoitmentType.values());
        typeCombo.setSelectedItem(appointment.getType());

        // Status combo box
        JLabel statusLabel = new JLabel("Status:");
        JComboBox<AppointementStatus> statusCombo = new JComboBox<>(AppointementStatus.values());
        statusCombo.setSelectedItem(appointment.getStatus());

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                appointment.setTime(LocalTime.parse(timeField.getText()));
                appointment.setDate(LocalDate.parse(dateField.getText()));
                appointment.setType((AppoitmentType) typeCombo.getSelectedItem());
                appointment.setStatus((AppointementStatus) statusCombo.getSelectedItem());

                controller.updateAppointment(appointment);
                JOptionPane.showMessageDialog(this, "Appointment updated successfully!");
                this.dispose();
                appLayout.getNavbar().simulateTabClick("Appointments");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating appointment: " + ex.getMessage());
            }
        });

        // Add components to panel
        mainPanel.add(timeLabel);
        mainPanel.add(timeField);
        mainPanel.add(dateLabel);
        mainPanel.add(dateField);
        mainPanel.add(typeLabel);
        mainPanel.add(typeCombo);
        mainPanel.add(statusLabel);
        mainPanel.add(statusCombo);
        mainPanel.add(new JLabel()); // Spacing
        mainPanel.add(saveButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
