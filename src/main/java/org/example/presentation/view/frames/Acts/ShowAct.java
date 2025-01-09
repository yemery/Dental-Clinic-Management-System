package org.example.presentation.view.frames.Acts;

import org.example.model.Act;
import org.example.model.enums.ActCategory;
import org.example.presentation.controller.ActController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ShowAct extends Frame {
    public ShowAct(Long id) {
        // Set up the frame
        ActController controller = new ActController();
        Act act = controller.getAct(id);

        setTitle("Act Details Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Dispose this frame only

//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel for the table
        JPanel tablePanel = new JPanel(new GridLayout(2, 4, 0, 0)); // 2 rows, 4 columns

        // Add header row (field names)
        tablePanel.add(createCell("ID", true));
        tablePanel.add(createCell("Name", true));
        tablePanel.add(createCell("Base Price", true));
        tablePanel.add(createCell("Category", true));

        // Add value row (field values)
        tablePanel.add(createCell(String.valueOf(act.getId()), false));
        tablePanel.add(createCell(act.getName(), false));
        tablePanel.add(createCell(String.format("%.2f", act.getBasePrice()), false));
        tablePanel.add(createCell(act.getCategory().name(), false));

        // Add the table panel to the frame
        add(tablePanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    // Helper method to create a cell with borders and optional styling
    private JPanel createCell(String text, boolean isHeader) {
        JPanel cell = new JPanel(new BorderLayout());
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(isHeader ? label.getFont().deriveFont(Font.BOLD, 14f) : label.getFont().deriveFont(Font.PLAIN, 12f));

        // Set cell border for visual separation
        Border lineBorder = new LineBorder(Color.BLACK, 1); // Black line border
        Border paddingBorder = new EmptyBorder(5, 5, 5, 5); // Padding inside the cell
        cell.setBorder(new CompoundBorder(lineBorder, paddingBorder));

        cell.add(label, BorderLayout.CENTER);
        return cell;
    }
}
