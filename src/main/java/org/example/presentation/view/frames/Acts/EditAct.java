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

public class EditAct extends Frame {

    private AppLayout appLayout;

    private Input name = new Input("Name", String.class);
    private Input basePrice = new Input("Base Price", Double.class);
    ActCategory[] categories = ActCategory.values();
    private Input category = new Input<>("Category Type", categories);

    public EditAct(Long actId, AppLayout appLayout){
        super();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.appLayout = appLayout;

        ActController actController = new ActController();
        Act editableAct = actController.getAct(actId);

        this.name.setValue(editableAct.getName());
        this.basePrice.setValue(editableAct.getBasePrice());
        this.category.setValue(editableAct.getCategory());

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
            editableAct.setName((String) name.getValue());
            editableAct.setBasePrice((Double) basePrice.getValue());
            editableAct.setCategory((ActCategory) category.getValue());

            actController.updateAct(editableAct);
            JOptionPane.showMessageDialog(this, "Intervention saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose(); // close the frame
            appLayout.getNavbar().simulateTabClick("Acts");

        });
        this.add(submitBtn, gbc);
        this.setVisible(true);
}}
