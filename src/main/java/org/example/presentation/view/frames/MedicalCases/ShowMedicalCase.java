package org.example.presentation.view.frames.MedicalCases;

import org.example.model.*;
import org.example.presentation.controller.*;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class ShowMedicalCase extends Frame {
    public ShowMedicalCase(Long medicalCaseId) {
        super();

        // Fetch medical case details
        MedicalCaseController medicalCaseController = new MedicalCaseController();
        MedicalCase medicalCase = medicalCaseController.getMedicalCaseById(medicalCaseId);

        if (medicalCase == null) {
            JOptionPane.showMessageDialog(this, "Medical Case not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Show Medical Case");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 600); // Initial size
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
        gbc.insets = new Insets(8, 0, 8, 0); // Add 8px gap between rows

        // Patient ID label
        JLabel patientLabel = new JLabel("Patient ID:");
        patientLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 0; // First row
        contentPanel.add(patientLabel, gbc);

        // Patient ID value
        JLabel patientValue = new JLabel(String.valueOf(medicalCase.getPatient()));
        patientValue.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy++; // Next row
        contentPanel.add(patientValue, gbc);

        // Creation Date label
        JLabel creationDateLabel = new JLabel("Creation Date:");
        creationDateLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++; // Next row
        contentPanel.add(creationDateLabel, gbc);

        // Creation Date value
        JLabel creationDateValue = new JLabel(medicalCase.getCreationDate().toString());
        creationDateValue.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy++; // Next row
        contentPanel.add(creationDateValue, gbc);

        // Appointments label
        JLabel appointmentsLabel = new JLabel("Associated Appointments:");
        appointmentsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++; // Next row
        contentPanel.add(appointmentsLabel, gbc);

        // Fetch appointments data and prepare table
        List<Long> appointmentIds = medicalCase.getAppointments();
        String[] appointmentColumns = {"Appointment ID"};
        Object[][] appointmentData = appointmentIds.stream()
                .map(id -> new Object[]{id})
                .toArray(Object[][]::new);

        if (appointmentData.length == 0) {
            System.out.println("No appointments found for Medical Case ID: " + medicalCaseId);
        }

        JTable appointmentsTable = new JTable(appointmentData, appointmentColumns);
        styleTable(appointmentsTable, 100);
        JScrollPane appointmentsScrollPane = new JScrollPane(appointmentsTable);
        gbc.gridy++; // Next row
        contentPanel.add(appointmentsScrollPane, gbc);

        // Medical Histories label
        JLabel medicalHistoriesLabel = new JLabel("Associated Medical Histories:");
        medicalHistoriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++;
        contentPanel.add(medicalHistoriesLabel, gbc);

        // Fetch medical histories data and prepare table
        List<Long> medicalHistoryIds = medicalCase.getMedicalHistories();
        String[] medicalHistoryColumns = {"Medical History ID"};
        Object[][] medicalHistoryData = medicalHistoryIds.stream()
                .map(id -> new Object[]{id}) // Map each ID to a table row
                .toArray(Object[][]::new);

        if (medicalHistoryData.length == 0) {
            System.out.println("No medical histories found for Medical Case ID: " + medicalCaseId);
        }

        JTable medicalHistoriesTable = new JTable(medicalHistoryData, medicalHistoryColumns);
        styleTable(medicalHistoriesTable, 150);
        JScrollPane medicalHistoriesScrollPane = new JScrollPane(medicalHistoriesTable);
        gbc.gridy++; // Next row
        contentPanel.add(medicalHistoriesScrollPane, gbc);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private void styleTable(JTable table, int columnWidth) {
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(25);

        // Adjust column widths
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(columnWidth); // Single column

        // Center align the data in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
    }
}
