package org.example.presentation.view.frames.Interventions;

import org.example.model.Act;
import org.example.model.Intervention;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.InterventionController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;

public class ShowIntervention extends Frame {
    public ShowIntervention(Long interventionId) {
        super();

        // Fetch intervention details
        InterventionController interventionController = new InterventionController();
        Intervention intervention = interventionController.getIntervention(interventionId);

        // Frame setup
        setTitle("Show Intervention");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(800, 400); // Initial size
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

        // Price label
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy = 0; // First row
        contentPanel.add(priceLabel, gbc);

        // Price value
        JLabel priceValue = new JLabel(String.format("%.2f", intervention.getPrice()));
        priceValue.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy++; // Next row
        contentPanel.add(priceValue, gbc);

        // Acts label
        JLabel actsLabel = new JLabel("Associated Acts:");
        actsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridy++; // Next row
        contentPanel.add(actsLabel, gbc);

        // Fetch acts data and prepare table
        List<Long> actIds = intervention.getActs();
        ActController actController = new ActController();

        String[] columnNames = {"Name", "Base Price", "Category"};
        Object[][] rowData = actIds.stream()
                .map(actController::getAct) // Fetch Act details
                .filter(act -> act != null) // Ensure non-null acts
                .map(act -> new Object[]{
                        act.getName(),
                        String.format("%.2f", act.getBasePrice()),
                        act.getCategory() != null ? act.getCategory().toString() : "N/A"
                })
                .toArray(Object[][]::new);

        JTable actsTable = new JTable(rowData, columnNames);
        actsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        actsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        actsTable.setRowHeight(25);

        // Adjust column widths
        TableColumnModel columnModel = actsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200); // Name
        columnModel.getColumn(1).setPreferredWidth(100); // Base Price
        columnModel.getColumn(2).setPreferredWidth(150); // Category

        // Center align the data in the columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set preferred size based on row count
        int tableHeight = actsTable.getRowHeight() * rowData.length + actsTable.getTableHeader().getHeight();
        actsTable.setPreferredScrollableViewportSize(new Dimension(700, tableHeight));

        JScrollPane scrollPane = new JScrollPane(actsTable);
        gbc.gridy++; // Next row
        contentPanel.add(scrollPane, gbc);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
