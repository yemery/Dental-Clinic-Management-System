package org.example.presentation.view.frames.MedicalCases;

import org.example.model.*;
import org.example.presentation.controller.*;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.records.AppointmentPatientInfo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class ShowMedicalCase extends Frame {
    public ShowMedicalCase(Long medicalCaseId) {
        super();

        // Initialize all required controllers
        MedicalCaseController medicalCaseController = new MedicalCaseController();
        PatientController patientController = new PatientController();
        AppointmentController appointmentController = new AppointmentController();
        MedicsHistoryController medicalHistoryController = new MedicsHistoryController();

        MedicalCase medicalCase = medicalCaseController.getMedicalCaseById(medicalCaseId);

        // Verify data loading
        if (medicalCase == null) {
            System.err.println("Failed to load medical case with ID: " + medicalCaseId);
            return;
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 0, 8, 0);
        gbc.weightx = 1.0;

        // Patient Information
        Patient patient = patientController.getPatient(medicalCase.getPatient());
        if (patient != null) {
            addSectionHeader("Patient Information", contentPanel, gbc);
            addField("Name: " + patient.getFirstName() + " " + patient.getLastName(), contentPanel, gbc);
            addField("CIN: " + patient.getCIN(), contentPanel, gbc);
            addField("Phone: " + patient.getPhone(), contentPanel, gbc);
        }

        // Medical Case Information
        addSectionHeader("Medical Case Information", contentPanel, gbc);
        addField("Creation Date: " + medicalCase.getCreationDate(), contentPanel, gbc);

        // Appointments Section
        addSectionHeader("Associated Appointments", contentPanel, gbc);
        List<Long> appointmentIds = medicalCase.getAppointments();
        if (appointmentIds != null && !appointmentIds.isEmpty()) {
            String[] appointmentColumns = {"ID", "Date", "Time", "Type", "Status", "Consultation"};
            List<Object[]> appointmentRows = new ArrayList<>();

            for (Long appointmentId : appointmentIds) {
                Appointment appointment = appointmentController.getAppointment(appointmentId);
                if (appointment != null) {
                    appointmentRows.add(new Object[]{
                            appointment.getId(),
                            appointment.getDate(),
                            appointment.getTime(),
                            appointment.getType(),
                            appointment.getStatus(),
                            appointment.getConsultation() == null || appointment.getConsultation() == 0
                                    ? "No Consultation"
                                    : appointment.getConsultation()
                    });
                }
            }

            if (!appointmentRows.isEmpty()) {
                Object[][] appointmentData = appointmentRows.toArray(new Object[0][]);
                JTable appointmentsTable = new JTable(appointmentData, appointmentColumns);
                styleTable(appointmentsTable, new int[]{50, 100, 100, 150, 100, 150});
                JScrollPane appointmentsScrollPane = new JScrollPane(appointmentsTable);
                appointmentsScrollPane.setPreferredSize(new Dimension(0, 150));
                gbc.gridy++;
                contentPanel.add(appointmentsScrollPane, gbc);
            } else {
                addField("No appointments found", contentPanel, gbc);
            }
        }

        // Medical Histories Section
        addSectionHeader("Medical Histories", contentPanel, gbc);
        List<Long> medicalHistoryIds = medicalCase.getMedicalHistories();
        if (medicalHistoryIds != null && !medicalHistoryIds.isEmpty()) {
            String[] historyColumns = {"ID", "Label", "Category", "Description", "Risk"};
            List<Object[]> historyRows = new ArrayList<>();

            for (Long historyId : medicalHistoryIds) {
                MedicalHistory history = medicalHistoryController.getMh(historyId);
                if (history != null) {
                    historyRows.add(new Object[]{
                            history.getId(),
                            history.getLabel(),
                            history.getCategory(),
                            history.getDescription(),
                            history.getRisk()
                    });
                }
            }

            if (!historyRows.isEmpty()) {
                Object[][] historyData = historyRows.toArray(new Object[0][]);
                JTable historiesTable = new JTable(historyData, historyColumns);
                styleTable(historiesTable, new int[]{50, 150, 100, 250, 100});
                JScrollPane historiesScrollPane = new JScrollPane(historiesTable);
                historiesScrollPane.setPreferredSize(new Dimension(0, 150));
                gbc.gridy++;
                contentPanel.add(historiesScrollPane, gbc);
            } else {
                addField("No medical histories found", contentPanel, gbc);
            }
        }

        // Add content panel to frame with scroll capability
        JScrollPane mainScrollPane = new JScrollPane(contentPanel);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(mainScrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void addSectionHeader(String text, JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridy++;
        gbc.insets = new Insets(20, 0, 10, 0);
        panel.add(label, gbc);
        gbc.insets = new Insets(8, 0, 8, 0);
    }

    private void addField(String text, JPanel panel, GridBagConstraints gbc) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy++;
        panel.add(label, gbc);
    }

    private void styleTable(JTable table, int[] columnWidths) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(25);

        // Set column widths
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < Math.min(columnWidths.length, table.getColumnCount()); i++) {
            columnModel.getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }
    }
}