package org.example.presentation.view.frames.Acts;

import org.example.model.Act;
import org.example.model.enums.ActCategory;
import org.example.presentation.controller.ActController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddAct extends Frame {
    private Input name = new Input("Name", String.class);
    private Input basePrice = new Input("Base Price", Double.class);
    ActCategory[] categories = ActCategory.values();
    private Input category = new Input<>("Category Type", categories);

    private AppLayout appLayout;

    public AddAct(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Set the close operation for this frame only


        // Window listener to handle cleanup on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // Trigger the "Acts" tab on the parent navigation bar
                appLayout.getNavbar().simulateTabClick("Acts");
            }
        });

        // Set layout
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configure layout constraints
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0);
        this.add(name, gbc);
        this.add(basePrice, gbc);
        this.add(category, gbc);

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.addActionListener(e -> {
            // Add the new act using ActController
            try {
                ActController actController = new ActController();
                actController.addNewAct(new Act(
                        (String) name.getValue(),
                        (Double) basePrice.getValue(),
                        (ActCategory) category.getValue()
                ));
                JOptionPane.showMessageDialog(this, "Act added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the frame
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(submitBtn, gbc);
        this.setLocationRelativeTo(null); // Center the frame
        this.setVisible(true);
    }
}
