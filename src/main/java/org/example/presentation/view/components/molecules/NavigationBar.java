package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NavigationBar extends JPanel {
    private JButton selectedButton = null;
    private Color defaultBgColor = new Color(0x3730a3);
    private Color selectedBgColor = new Color(143, 143, 246);
    private Color hoverBgColor = new Color(0x9891F1);

    public NavigationBar(String... tabs) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(-10, -10, -10, -10));
        setBackground(new Color(0x3730a3));

//        String[] tabs = {"Dashboard", "Patients", "Appointments", "Acts", "Consultations"};

        for (String tabName : tabs) {
            JButton tabButton = createTabButton(tabName);
            tabButton.setForeground(Color.WHITE);
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
        button.setBackground(new Color(0x3730a3));
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