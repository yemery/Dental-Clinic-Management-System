package org.example.presentation.view.layouts;

import org.example.model.enums.UserType;
import org.example.presentation.controller.StaffController;
import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.frames.Frame;
import org.example.utils.NavbarLinks;

import javax.swing.*;
import java.awt.*;

public class AuthView extends Frame {
    private JTextField login;
    private JPasswordField pwd;

    public AuthView() {
        super();

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // flex-col
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 0, 10, 0); // gap

        // Login field
        login = new JTextField(30);
        login.setPreferredSize(new Dimension(300, 40));
        JLabel loginLabel = new JLabel("Login");
        this.add(loginLabel, gbc);
        this.add(login, gbc);

        // Password field
        pwd = new JPasswordField(30);
        pwd.setPreferredSize(new Dimension(300, 40));
        JLabel pwdLabel = new JLabel("Password");
        this.add(pwdLabel, gbc);
        this.add(pwd, gbc);

        // Submit button
        JButton submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(300, 40));
        submitBtn.addActionListener(e -> {
            String loginValue = login.getText();
            String pwdValue = new String(pwd.getPassword());

            StaffController staffController = new StaffController();

            try {
                Long userId = staffController.login(loginValue, pwdValue);
                if (userId != null) {  // Only proceed if login was successful
                    UserType userType = staffController.getRole(userId);
                    NavbarLinks navbarLinks = new NavbarLinks();
                    String[] getNavbar = navbarLinks.getNavlinks(userType);

                    JOptionPane.showMessageDialog(this, "Login successful. Welcome back",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new AppLayout(getNavbar);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        this.add(submitBtn, gbc);

        this.setVisible(true);
    }
}
