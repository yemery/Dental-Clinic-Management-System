package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavigationBar extends JPanel {
    private JButton selectedButton = null;
    private Color defaultBgColor = new Color(240, 240, 240);
    private Color selectedBgColor = new Color(200, 200, 255);
    private Color hoverBgColor = new Color(220, 220, 255);

    public NavigationBar() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setBackground(Color.WHITE);

        String[] tabs = {"Dashboard", "Patients", "Appointments", "Acts", "Consultations"};

        for (String tabName : tabs) {
            JButton tabButton = createTabButton(tabName);
            add(tabButton);

            // Set Dashboard as default selected
            if (tabName.equals("Dashboard")) {
                selectedButton = tabButton;
                tabButton.setBackground(selectedBgColor);
            }
        }
    }

    private JButton createTabButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(defaultBgColor);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground(hoverBgColor);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground(defaultBgColor);
                }
            }
        });

        button.addActionListener(e -> {
            if (selectedButton != null) {
                selectedButton.setBackground(defaultBgColor);
            }
            selectedButton = button;
            button.setBackground(selectedBgColor);
        });

        return button;
    }

    // Method to add action listeners from outside
    public void addTabListener(String tabName, ActionListener listener) {
        for (Component comp : getComponents()) {
            if (comp instanceof JButton && ((JButton) comp).getText().equals(tabName)) {
                ((JButton) comp).addActionListener(listener);
            }
        }
    }
}