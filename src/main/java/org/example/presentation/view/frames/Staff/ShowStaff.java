package org.example.presentation.view.frames.Staff;

import org.example.model.Staff;
import org.example.presentation.controller.StaffController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowStaff extends Frame {
    public ShowStaff(Long staffId) {
        super();

        // Fetch staff details
        StaffController controller = new StaffController();
        Staff staff = controller.getUser(staffId);

        if (staff == null) {
            JOptionPane.showMessageDialog(this, "Staff not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Staff Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Staff details panel
        JPanel detailsPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Staff Details"));

        detailsPanel.add(new JLabel("ID:"));
        detailsPanel.add(new JLabel(String.valueOf(staff.getId())));

        detailsPanel.add(new JLabel("First Name:"));
        detailsPanel.add(new JLabel(staff.getFirstName()));

        detailsPanel.add(new JLabel("Last Name:"));
        detailsPanel.add(new JLabel(staff.getLastName()));

        detailsPanel.add(new JLabel("CIN:"));
        detailsPanel.add(new JLabel(staff.getCIN()));

        detailsPanel.add(new JLabel("Phone:"));
        detailsPanel.add(new JLabel(staff.getPhone()));

        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(new JLabel(staff.getEmail()));

        detailsPanel.add(new JLabel("User Type:"));
        detailsPanel.add(new JLabel(staff.getUserType().toString()));

        // Add details panel to main panel
        mainPanel.add(detailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
