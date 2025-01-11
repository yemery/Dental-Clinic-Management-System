package org.example.presentation.view.frames.Appoitments;

import org.example.model.Appointment;
import org.example.presentation.controller.AppointmentController;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.controller.ConsultationController;
import org.example.model.Consultation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class ShowAppointment extends Frame {
    public ShowAppointment(Long appointmentId) {
        super();

        AppointmentController controller = new AppointmentController();
        Appointment appointment = controller.getAppointment(appointmentId);
        ConsultationController consultationController = new ConsultationController();
        Consultation consultation = consultationController.getConsultation(appointment.getConsultation());

        // Frame setup
        setTitle("Appointment Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(900, 600);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Appointment details panel
        JPanel appointmentDetailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        appointmentDetailsPanel.setBorder(BorderFactory.createTitledBorder("Appointment Details"));

        appointmentDetailsPanel.add(new JLabel("ID:"));
        appointmentDetailsPanel.add(new JLabel(appointment.getId().toString()));

        appointmentDetailsPanel.add(new JLabel("Date:"));
        appointmentDetailsPanel.add(new JLabel(appointment.getDate().toString()));

        appointmentDetailsPanel.add(new JLabel("Time:"));
        appointmentDetailsPanel.add(new JLabel(appointment.getTime().toString()));

        appointmentDetailsPanel.add(new JLabel("Type:"));
        appointmentDetailsPanel.add(new JLabel(appointment.getType().toString()));

        appointmentDetailsPanel.add(new JLabel("Status:"));
        appointmentDetailsPanel.add(new JLabel(appointment.getStatus().toString()));

        mainPanel.add(appointmentDetailsPanel);

        // Consultation details panel
        JPanel consultationDetailsPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        consultationDetailsPanel.setBorder(BorderFactory.createTitledBorder("Consultation Details"));

        consultationDetailsPanel.add(new JLabel("Type:"));
        consultationDetailsPanel.add(new JLabel(consultation != null ? consultation.getType().toString() : "N/A"));

        consultationDetailsPanel.add(new JLabel("Note:"));
        consultationDetailsPanel.add(new JLabel(consultation != null ? consultation.getNote() : "N/A"));

        consultationDetailsPanel.add(new JLabel("Date:"));
        consultationDetailsPanel.add(new JLabel(consultation != null ? consultation.getDate().toString() : "N/A"));

        consultationDetailsPanel.add(new JLabel("Certificate:"));
        consultationDetailsPanel.add(new JLabel(consultation != null ? String.valueOf(consultation.getCertificate()) : "N/A"));

        consultationDetailsPanel.add(new JLabel("Prescription:"));
        consultationDetailsPanel.add(new JLabel(consultation != null ? String.valueOf(consultation.getPrescription()) : "N/A"));

        mainPanel.add(consultationDetailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
