package org.example.presentation.view.frames.Interventions;

import org.example.model.Act;
import org.example.model.Intervention;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.InterventionController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class AddIntervention extends Frame {
    private Input price = new Input("Price", Double.class);
    private JList<Act> actList; // Multi-select list for acts
    private JButton saveButton;
    private AppLayout appLayout;

//    public AddIntervention(AppLayout appLayout) {
//        super();
//        this.appLayout = appLayout;
//
//        // Controller to fetch available acts
//        ActController actController = new ActController();
//        List<Act> availableActs = actController.displayAllActs();
//
//        // Frame setup
//        setTitle("Add Intervention");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
//        setSize(600, 400);
//        setLocationRelativeTo(null); // Center on screen
//        setLayout(new BorderLayout());
//
//        // Main content panel
//        JPanel contentPanel = new JPanel();
//        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
//        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Add padding
//
//        // Add price input field
//        contentPanel.add(price);
//
//        // Add label for act selection
//        JLabel actLabel = new JLabel("Select Acts:");
//        actLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//        contentPanel.add(actLabel);
//
//        // Create and add multi-select list for acts
//        actList = new JList<>(availableActs.toArray(new Act[0]));
//        actList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        actList.setVisibleRowCount(5); // Limit visible rows
//
//        // Custom renderer to display only the name in the JList
//        actList.setCellRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Act) {
//                    renderer.setText(((Act) value).getName()); // Display the name of the Act
//                }
//                return renderer;
//            }
//        });
//
//        JScrollPane scrollPane = new JScrollPane(actList); // Add scroll pane
//        scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
//        contentPanel.add(scrollPane);
//
//        // Add save button
//        saveButton = new JButton("Save");
//        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        saveButton.addActionListener(e -> saveIntervention());
//
//        contentPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
//        contentPanel.add(saveButton);
//
//        // Add content panel to frame
//        add(contentPanel, BorderLayout.CENTER);
//
//        // Make the frame visible
//        setVisible(true);
//    }
    public AddIntervention(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Controller to fetch available acts
        ActController actController = new ActController();
        List<Act> availableActs = actController.displayAllActs();

        // Frame setup
        setTitle("Add Intervention");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel with GridBagLayout
        JPanel contentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; // Single column
        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
        gbc.insets = new Insets(8, 0, 8, 0); // Add 8px gap between rows

        // Add price input field
        gbc.gridy = 0; // First row
        contentPanel.add(price, gbc);

        // Add label for act selection
        JLabel actLabel = new JLabel("Select Acts:");
        actLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align text
        gbc.gridy++; // Next row
        contentPanel.add(actLabel, gbc);

        // Add multi-select list for acts with scroll pane
        actList = new JList<>(availableActs.toArray(new Act[0]));
        actList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        actList.setVisibleRowCount(5); // Limit visible rows

        // Custom renderer to display only the name in the JList
        actList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Act) {
                    renderer.setText(((Act) value).getName()); // Display the name of the Act
                }
                return renderer;
            }
        });

        JScrollPane scrollPane = new JScrollPane(actList); // Add scroll pane
        gbc.gridy++; // Next row
        gbc.ipady = 50; // Increase height for the scroll pane
        contentPanel.add(scrollPane, gbc);
        gbc.ipady = 0; // Reset padding

        // Add save button
        saveButton = new Button("Save");
        saveButton.addActionListener(e -> saveIntervention());
        gbc.gridy++; // Next row
        contentPanel.add(saveButton, gbc);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }


    // Method to save intervention
    private void saveIntervention() {
        try {

            Object priceValue = price.getValue();
            if (priceValue== null) throw new IllegalArgumentException("Price is required.");


            // Get selected acts
            List<Act> selectedActs = actList.getSelectedValuesList();
            if (selectedActs.isEmpty()) {
                throw new IllegalArgumentException("At least one act must be selected.");
            }

            // Example of handling the intervention data
            System.out.println("Price: " + priceValue);
            System.out.println("Selected Acts: ");
            selectedActs.forEach(act -> System.out.println("- " + act.getId()));

            InterventionController interventionC = new InterventionController();
            Intervention intervention = new Intervention((double)priceValue);
            intervention.setActs(selectedActs.stream().map(Act::getId).toList());



            interventionC.addIntervention(
                    intervention
            );
            // Close the window after saving

            JOptionPane.showMessageDialog(this, "Intervention saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Interventions");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
