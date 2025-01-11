package org.example.presentation.view.frames.Appoitments;

import org.example.model.Appointment;
import org.example.model.enums.*;
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

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 250, 50, 250));

        date.setAlignmentX(Component.CENTER_ALIGNMENT);
        time.setAlignmentX(Component.CENTER_ALIGNMENT);
        type.setAlignmentX(Component.CENTER_ALIGNMENT);
        status.setAlignmentX(Component.CENTER_ALIGNMENT);

        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        submitBtn.addActionListener(e -> {
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
                        selectedTime, selectedDate, (AppoitmentType) type.getSelectedItem(), (AppointementStatus) status.getSelectedItem()
                ));

                JOptionPane.showMessageDialog(this, "Appointment saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                appLayout.getNavbar().simulateTabClick("Appointments");
                this.dispose();

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(date);
        panel.add(Box.createVerticalStrut(10));
        panel.add(time);
        panel.add(Box.createVerticalStrut(10));
        panel.add(type);
        panel.add(Box.createVerticalStrut(10));
        panel.add(status);
        panel.add(Box.createVerticalStrut(20));
        panel.add(submitBtn);

        this.add(panel);
        this.setVisible(true);
    }
}
