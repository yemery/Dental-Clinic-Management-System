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
import java.util.ArrayList;
import java.util.List;

public class EditIntervention extends Frame {
    private Input price;
    private JComboBox<Act> currentActComboBox; // ComboBox for current acts
    private JComboBox<Act> availableActComboBox; // ComboBox for available acts
    private JButton saveButton;
    private JButton removeActButton;
    private JButton addActButton;
    private AppLayout appLayout;

    private Intervention intervention;

//    public EditIntervention( Long interventionId,AppLayout appLayout) {
//        super();
//        this.appLayout = appLayout;
//
//        // Controller to fetch intervention and acts
//        InterventionController interventionController = new InterventionController();
//        ActController actController = new ActController();
//
//        // Fetch the intervention details
//        this.intervention = interventionController.getIntervention(interventionId);
//
//        // Fetch available acts
//        List<Act> allActs = actController.displayAllActs();
//        List<Long> currentActs = intervention.getActs();
//        List<Act> availableActs = allActs.stream()
//                .filter(act -> !currentActs.contains(act.getId()))
//                .toList();
//
//        // Frame setup
//        setTitle("Edit Intervention");
//        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
//        setSize(800, 600);
//        setLocationRelativeTo(null); // Center on screen
//        setLayout(new BorderLayout());
//
//        // Main content panel with GridBagLayout
//        JPanel contentPanel = new JPanel(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        gbc.gridx = 0; // Single column
//        gbc.anchor = GridBagConstraints.CENTER; // Center alignment
//        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
//        gbc.insets = new Insets(8, 0, 8, 0); // Add 8px gap between rows
//
//        // Price input field
//        price = new Input("Price", Double.class);
//        price.setValue(intervention.getPrice());
//        gbc.gridy = 0; // First row
//        contentPanel.add(price, gbc);
//
//        // Current acts label and ComboBox
//        JLabel currentActsLabel = new JLabel("Current Acts:");
//        currentActsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        gbc.gridy++; // Next row
//        contentPanel.add(currentActsLabel, gbc);
//
//        currentActComboBox = new JComboBox<>(currentActs.toArray(new Act[0]));
//        currentActComboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Act) {
//                    renderer.setText(((Act) value).getName()); // Display the name of the Act
//                }
//                return renderer;
//            }
//        });
//        gbc.gridy++; // Next row
//        contentPanel.add(currentActComboBox, gbc);
//
//        // Remove act button
//        removeActButton = new Button("Remove Act");
//        removeActButton.addActionListener(e -> removeAct());
//        gbc.gridy++; // Next row
//        contentPanel.add(removeActButton, gbc);
//
//        // Available acts label and ComboBox
//        JLabel availableActsLabel = new JLabel("Available Acts:");
//        availableActsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        gbc.gridy++; // Next row
//        contentPanel.add(availableActsLabel, gbc);
//
//        availableActComboBox = new JComboBox<>(availableActs.toArray(new Act[0]));
//        availableActComboBox.setRenderer(new DefaultListCellRenderer() {
//            @Override
//            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//                JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
//                if (value instanceof Act) {
//                    renderer.setText(((Act) value).getName()); // Display the name of the Act
//                }
//                return renderer;
//            }
//        });
//        gbc.gridy++; // Next row
//        contentPanel.add(availableActComboBox, gbc);
//
//        // Add act button
//        addActButton = new Button("Add Act");
//        addActButton.addActionListener(e -> addAct());
//        gbc.gridy++; // Next row
//        contentPanel.add(addActButton, gbc);
//
//        // Save button
//        saveButton = new Button("Save Changes");
//        saveButton.addActionListener(e -> saveIntervention());
//        gbc.gridy++; // Next row
//        contentPanel.add(saveButton, gbc);
//
//        // Add content panel to frame
//        add(contentPanel, BorderLayout.CENTER);
//
//        // Make the frame visible
//        setVisible(true);
//    }
public EditIntervention(Long interventionId, AppLayout appLayout) {
    super();
    this.appLayout = appLayout;

    // Controller to fetch intervention and acts
    InterventionController interventionController = new InterventionController();
    ActController actController = new ActController();

    // Fetch the intervention details
    this.intervention = interventionController.getIntervention(interventionId);

    // Fetch available acts
    List<Act> allActs = actController.displayAllActs();
    List<Long> currentActIds = intervention.getActs(); // List of act IDs in the intervention
    List<Act> currentActs = allActs.stream()
            .filter(act -> currentActIds.contains(act.getId()))
            .toList(); // Convert IDs to Act objects
    List<Act> availableActs = allActs.stream()
            .filter(act -> !currentActIds.contains(act.getId()))
            .toList(); // Acts not in the intervention

    // Frame setup
    setTitle("Edit Intervention");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
    setSize(800, 600);
    setLocationRelativeTo(null); // Center on screen
    setLayout(new BorderLayout());

    // Main content panel with GridBagLayout
    JPanel contentPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0; // Single column
    gbc.anchor = GridBagConstraints.CENTER; // Center alignment
    gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
    gbc.insets = new Insets(8, 0, 8, 0); // Add 8px gap between rows

    // Price input field
    price = new Input("Price", Double.class);
    price.setValue(intervention.getPrice());
    gbc.gridy = 0; // First row
    contentPanel.add(price, gbc);

    // Current acts label and ComboBox
    JLabel currentActsLabel = new JLabel("Current Acts:");
    currentActsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridy++; // Next row
    contentPanel.add(currentActsLabel, gbc);

    currentActComboBox = new JComboBox<>(currentActs.toArray(new Act[0]));
    currentActComboBox.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Act) {
                renderer.setText(((Act) value).getName()); // Display the name of the Act
            }
            return renderer;
        }
    });
    gbc.gridy++; // Next row
    contentPanel.add(currentActComboBox, gbc);

    // Remove act button
    removeActButton = new Button("Remove Act");
    removeActButton.addActionListener(e -> removeAct());
    gbc.gridy++; // Next row
    contentPanel.add(removeActButton, gbc);

    // Available acts label and ComboBox
    JLabel availableActsLabel = new JLabel("Available Acts:");
    availableActsLabel.setHorizontalAlignment(SwingConstants.CENTER);
    gbc.gridy++; // Next row
    contentPanel.add(availableActsLabel, gbc);

    availableActComboBox = new JComboBox<>(availableActs.toArray(new Act[0]));
    availableActComboBox.setRenderer(new DefaultListCellRenderer() {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel renderer = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Act) {
                renderer.setText(((Act) value).getName()); // Display the name of the Act
            }
            return renderer;
        }
    });
    gbc.gridy++; // Next row
    contentPanel.add(availableActComboBox, gbc);

    // Add act button
    addActButton = new Button("Add Act");
    addActButton.addActionListener(e -> addAct());
    gbc.gridy++; // Next row
    contentPanel.add(addActButton, gbc);

    // Save button
    saveButton = new Button("Save Changes");
    saveButton.addActionListener(e -> saveIntervention());
    gbc.gridy++; // Next row
    contentPanel.add(saveButton, gbc);

    // Add content panel to frame
    add(contentPanel, BorderLayout.CENTER);

    // Make the frame visible
    setVisible(true);
}

    private void removeAct() {
        Act selectedAct = (Act) currentActComboBox.getSelectedItem();
        if (selectedAct == null) {
            JOptionPane.showMessageDialog(this, "Please select an act to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        currentActComboBox.removeItem(selectedAct);
        availableActComboBox.addItem(selectedAct);
    }

    private void addAct() {
        Act selectedAct = (Act) availableActComboBox.getSelectedItem();
        if (selectedAct == null) {
            JOptionPane.showMessageDialog(this, "Please select an act to add.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        availableActComboBox.removeItem(selectedAct);
        currentActComboBox.addItem(selectedAct);
    }

    private void saveIntervention() {
        try {
            Object priceValue = price.getValue();
            if (priceValue == null) {
                throw new IllegalArgumentException("Price is required.");
            }

            double parsedPrice = Double.parseDouble(priceValue.toString());
            intervention.setPrice(parsedPrice);

            // Get updated acts
            List<Act> updatedActs = new ArrayList<>();
            for (int i = 0; i < currentActComboBox.getItemCount(); i++) {
                updatedActs.add(currentActComboBox.getItemAt(i));
            }
            intervention.setActs(updatedActs.stream().map(Act::getId).toList()); // Save IDs only

            InterventionController interventionController = new InterventionController();
            interventionController.updateIntervention(intervention);

            JOptionPane.showMessageDialog(this, "Intervention updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Interventions");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
