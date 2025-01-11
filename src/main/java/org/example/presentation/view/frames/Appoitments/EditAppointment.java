package org.example.presentation.view.frames.Appoitments;

import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;
import org.example.model.Appointment;
import org.example.model.Consultation;
import org.example.model.enums.AppointementStatus;
import org.example.model.enums.AppoitmentType;
import org.example.presentation.controller.AppointmentController;
import org.example.presentation.controller.ConsultationController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EditAppointment extends Frame {
    private AppLayout appLayout;
    private Appointment appointment;
    private AppointmentController controller;
    private JComboBox<Consultation> consultationComboBox;

    public EditAppointment(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new AppointmentController();

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
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
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

        // Consultation combo box
        JLabel consultationLabel = new JLabel("Consultation:");
        ConsultationController consultationController = new ConsultationController();
        List<Consultation> consultations = consultationController.displayAllConsultations();
        consultationComboBox = new JComboBox<>(consultations.toArray(new Consultation[0]));
        consultationComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Consultation) {
                    renderer.setText(String.valueOf(((Consultation) value).getId()));
                }
                return renderer;
            }
        });

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                appointment.setTime(LocalTime.parse(timeField.getText()));
                appointment.setDate(LocalDate.parse(dateField.getText()));
                appointment.setType((AppoitmentType) typeCombo.getSelectedItem());
                appointment.setStatus((AppointementStatus) statusCombo.getSelectedItem());

                Consultation selectedConsultation = (Consultation) consultationComboBox.getSelectedItem();
                appointment.setConsultations(selectedConsultation != null ? Long.valueOf(selectedConsultation.getId()) : null);

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
        mainPanel.add(consultationLabel);
        mainPanel.add(consultationComboBox);
        mainPanel.add(new JLabel()); // Spacing
        mainPanel.add(saveButton);

        this.add(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
