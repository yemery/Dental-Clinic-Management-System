package org.example.presentation.view.frames.Prescriptions;

import org.example.model.Medicine;
import org.example.model.Prescription;
import org.example.model.PrescriptionMedicine;
import org.example.presentation.controller.MedicineController;
import org.example.presentation.controller.PrescriptionController;
import org.example.presentation.controller.PrescriptionMedicineController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ShowPrescription extends Frame {
    public ShowPrescription(Long prescriptionId) {
        super();

        PrescriptionController prescriptionController = new PrescriptionController();
        PrescriptionMedicineController prescriptionMedicineController = new PrescriptionMedicineController();
        MedicineController medicineController = new MedicineController();

        Prescription prescription = prescriptionController.getPrescription(prescriptionId);

        if (prescription == null) {
            JOptionPane.showMessageDialog(this, "Prescription not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Prescription Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Prescription details panel
        JPanel prescriptionDetailsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        prescriptionDetailsPanel.setBorder(BorderFactory.createTitledBorder("Prescription Details"));

        prescriptionDetailsPanel.add(new JLabel("ID:"));
        prescriptionDetailsPanel.add(new JLabel(String.valueOf(prescription.getId())));

        prescriptionDetailsPanel.add(new JLabel("Date:"));
        prescriptionDetailsPanel.add(new JLabel(prescription.getDate().toString()));

        mainPanel.add(prescriptionDetailsPanel);

        // Medicines table
        List<Object[]> medicineData = prescription.getPrescriptionsMedicine().stream()
                .map(prescriptionMedicineController::getPrescriptionMedicine)
                .filter(pm -> pm != null && pm.getMedicine() != null)
                .map(pm -> {
                    Medicine medicine = medicineController.getMedicine(pm.getMedicine())    ;
                    return medicine != null
                            ? new Object[]{medicine.getName(), pm.getMin(), pm.getMax(), pm.getDescription()}
                            : new Object[]{"Unknown Medicine", pm.getMin(), pm.getMax(), pm.getDescription()};
                })
                .collect(Collectors.toList());

        String[] columnNames = {"Medicine Name", "Min Quantity", "Max Quantity", "Description"};
        DefaultTableModel tableModel = new DefaultTableModel(medicineData.toArray(new Object[0][]), columnNames);
        JTable medicineTable = new JTable(tableModel);
        medicineTable.setRowHeight(25);
        medicineTable.setFont(new Font("Arial", Font.PLAIN, 14));
        medicineTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        // Center align table cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < medicineTable.getColumnCount(); i++) {
            medicineTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(medicineTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Medicines"));
        mainPanel.add(scrollPane);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
