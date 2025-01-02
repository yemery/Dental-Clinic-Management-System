package org.example.presentation.view.layouts;


import org.example.presentation.view.components.molecules.NavigationBar;
import org.example.presentation.view.frames.Dashboard;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import java.awt.*;

public class AppLayout extends Frame {
    private NavigationBar navbar;
    private JPanel contentPanel; // content to be shown (pages)

    public AppLayout() {
        navbar = new NavigationBar();
        contentPanel = new JPanel();

        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(navbar, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setupNavigation();
        setVisible(true);
    }

    private void setupNavigation() {
        // kinda routing logic
        navbar.addTabListener("Dashboard", e -> {
//            JPanel dashboardPanel = new JPanel();  // to replace later with the dashboard panel (page)
//            dashboardPanel.add(new JLabel("Dashboard"));
            setContent(new Dashboard());
        });

        navbar.addTabListener("Patients", e -> {
            JPanel patientsPanel = new JPanel();
            patientsPanel.add(new JLabel("Patients"));
            setContent(patientsPanel);
        });

        navbar.addTabListener("Appointments", e -> {
            JPanel appointmentsPanel = new JPanel();
            appointmentsPanel.add(new JLabel("Appointments"));
            setContent(appointmentsPanel);
        });

        navbar.addTabListener("Acts", e -> {
            JPanel actsPanel = new JPanel();
            actsPanel.add(new JLabel("Acts"));
            setContent(actsPanel);
        });

        navbar.addTabListener("Consultations", e -> {
            JPanel consultationsPanel = new JPanel();
            consultationsPanel.add(new JLabel("Consultations"));
            setContent(consultationsPanel);
        });
    }

    private void setContent(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        new AppLayout();
    }
}