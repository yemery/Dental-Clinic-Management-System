package org.example.presentation.view.frames.Appoitments;

import org.example.model.Appointment;
import org.example.model.enums.AppoitmentType;
import org.example.model.enums.AppointementStatus;
import org.example.presentation.controller.AppointmentController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddAppointment extends Frame {
    private Input<LocalDate> date = new Input<>("Date (YYYY-MM-DD)", LocalDate.class);
    private Input<LocalTime> time = new Input<>("Time (HH:mm)", LocalTime.class);
    private JComboBox<AppoitmentType> type = new JComboBox<>(AppoitmentType.values());
    private JComboBox<AppointementStatus> status = new JComboBox<>(AppointementStatus.values());

    private AppLayout appLayout;

    public AddAppointment(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

        // Title label
        JLabel titleLabel = new JLabel("Add New Appointment");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        panel.add(titleLabel);

        // Create wrapper panels for combo boxes
        JPanel typeWrapper = createComboWrapper("Appointment Type:", type);
        JPanel statusWrapper = createComboWrapper("Status:", status);

        // Add input fields with proper spacing
        panel.add(date);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(time);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(typeWrapper);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(statusWrapper);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setMaximumSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16));
        submitBtn.addActionListener(e -> saveAppointment());
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);
        this.setVisible(true);
    }

    private JPanel createComboWrapper(String labelText, JComboBox<?> comboBox) {
        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setMaximumSize(new Dimension(300, 60));
        wrapper.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        comboBox.setMaximumSize(new Dimension(500, 30));
        comboBox.setPreferredSize(new Dimension(500, 30));
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));

        wrapper.add(label);
        wrapper.add(Box.createRigidArea(new Dimension(0, 5)));
        wrapper.add(comboBox);

        return wrapper;
    }

    private void saveAppointment() {
        try {
            LocalDate selectedDate = date.getValue();
            if (selectedDate == null) {
                throw new IllegalArgumentException("Date cannot be empty.");
            }

            LocalTime selectedTime = time.getValue();
            if (selectedTime == null) {
                throw new IllegalArgumentException("Time cannot be empty.");
            }

            AppointmentController controller = new AppointmentController();
            controller.addAppointment(new Appointment(
                    selectedTime, selectedDate,
                    (AppoitmentType) type.getSelectedItem(),
                    (AppointementStatus) status.getSelectedItem()
            ));

            JOptionPane.showMessageDialog(this,
                    "Appointment saved successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
            appLayout.getNavbar().simulateTabClick("Appointments");
            this.dispose();

        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this,
                    exception.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}