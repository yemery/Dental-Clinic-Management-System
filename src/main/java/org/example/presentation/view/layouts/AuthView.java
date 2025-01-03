package org.example.presentation.view.layouts;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import java.awt.*;

public class AuthView extends Frame {
    private Input login;
    private Input pwd;

    public AuthView() {
        super();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // flex-col
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // gap

        login = new Input<>("Login",String.class);
        pwd = new Input<>("Password",String.class);
        this.add(login, gbc);
        this.add(pwd, gbc);

        JButton submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.addActionListener(e -> {
            System.out.println(login.getValue() + ":" + pwd.getValue());
            // add auth controller logic later
        });
        this.add(submitBtn, gbc);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new AuthView();
    }
}