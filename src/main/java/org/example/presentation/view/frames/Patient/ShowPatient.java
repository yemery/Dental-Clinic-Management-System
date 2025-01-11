package org.example.presentation.view.frames.Patient;

import org.example.model.Patient;
import org.example.presentation.controller.PatientController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class ShowPatient extends Frame {
    public ShowPatient(Long patientId) {
        super();

        // Fetch patient details
        PatientController controller = new PatientController();
        Patient patient = controller.getPatient(patientId);

        // Frame setup
        setTitle("Show Patient Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 400); // Initial size
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Add Patient Details Table
        String[] columnNames = {"Field", "Value"};
        Object[][] rowData = {
                {"ID", String.valueOf(patient.getId())},
                {"First Name", patient.getFirstName()},
                {"Last Name", patient.getLastName()},
                {"Birth Date", patient.getBirthDate().toString()},
                {"Phone", patient.getPhone()},
                {"Email", patient.getEmail()},
                {"Gender", patient.getGender().toString()},
                {"Registration", patient.getRegistration()},
                {"Mutuelle", patient.getMutuelle().toString()},
                {"Job", patient.getJob()},
                {"CIN", patient.getCIN()},
                {"Address", patient.getAddress()}
        };

        JTable patientTable = new JTable(rowData, columnNames);
        patientTable.setFont(new Font("Arial", Font.PLAIN, 14));
        patientTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        patientTable.setRowHeight(25);

        // Adjust column widths
        TableColumnModel columnModel = patientTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200); // Field
        columnModel.getColumn(1).setPreferredWidth(400); // Value

        // Center align the data in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(patientTable);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add scroll pane directly to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
