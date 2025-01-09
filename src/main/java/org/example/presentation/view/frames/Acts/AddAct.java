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

        // window listener to detect closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                for (Component comp : appLayout.getNavbar().getComponents()) {
                    if (comp instanceof JButton && ((JButton) comp).getText().equals("Acts")) {
                        ((JButton) comp).doClick();
                        break;
                    }
                }
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


        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.addActionListener(e -> {
            // Add the new act using ActController
            ActController actController = new ActController();
            actController.addNewAct(new Act(
                    (String) name.getValue(),
                    (Double) basePrice.getValue(),
                    (ActCategory) category.getValue()
            ));
            JOptionPane.showMessageDialog(this, "Intervention saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose(); // close the frame
        });

        this.add(submitBtn, gbc);
        this.setVisible(true);
    }
}