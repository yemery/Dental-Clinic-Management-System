package org.example.presentation.view.frames.Acts;

import org.example.model.Act;
import org.example.presentation.controller.ActController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowAct extends Frame {
    public ShowAct(Long id) {
        super();

        // Fetch act details
        ActController controller = new ActController();
        Act act = controller.getAct(id);

        // Frame setup
        setTitle("Show Act Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(500, 300); // Initial size
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel with GridLayout
        JPanel contentPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // Flexible rows, 1 column
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add rows for each field
        contentPanel.add(createRow("ID:", String.valueOf(act.getId())));
        contentPanel.add(createRow("Name:", act.getName()));
        contentPanel.add(createRow("Base Price:", String.format("%.2f", act.getBasePrice())));
        contentPanel.add(createRow("Category:", act.getCategory() != null ? act.getCategory().name() : "N/A"));

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create a styled row with label and data
    private JPanel createRow(String label, String value) {
        JPanel row = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns
        JLabel labelComponent = new JLabel(label, SwingConstants.LEFT);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel valueComponent = new JLabel(value, SwingConstants.LEFT);
        valueComponent.setFont(new Font("Arial", Font.PLAIN, 14));

        row.add(labelComponent);
        row.add(valueComponent);

        return row;
    }
}
