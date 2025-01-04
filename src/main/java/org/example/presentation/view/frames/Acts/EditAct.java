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

public class EditAct extends Frame {

    private AppLayout appLayout;

    private Input name = new Input("Name", String.class);
    private Input basePrice = new Input("Base Price", Double.class);
    ActCategory[] categories = ActCategory.values();
    private Input category = new Input<>("Category Type", categories);

    public EditAct(Long actId, AppLayout appLayout){
        super();
        this.appLayout = appLayout;

        ActController actController = new ActController();
        Act editableAct = actController.getAct(actId);

        this.name.setValue(editableAct.getName());
        this.basePrice.setValue(editableAct.getBasePrice());
        this.category.setValue(editableAct.getCategory());



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


        org.example.presentation.view.components.atoms.Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.addActionListener(e -> {
            editableAct.setName((String) name.getValue());
            editableAct.setBasePrice((Double) basePrice.getValue());
            editableAct.setCategory((ActCategory) category.getValue());

            actController.updateAct(editableAct);

            dispose(); // close the frame
        });
        this.add(submitBtn, gbc);
        this.setVisible(true);
}}
