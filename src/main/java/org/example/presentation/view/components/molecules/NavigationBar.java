package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

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

    public void addTabListener(String tabName, ActionListener listener) {
        Arrays.stream(getComponents())
                .filter(comp -> comp instanceof JButton && tabName.equals(((JButton) comp).getText()))
                .map(comp -> (JButton) comp)
                .forEach(button -> button.addActionListener(listener));
    }
    public void simulateTabClick(String tabName) {
        // Find the tab button by name and simulate a click
        for (Component component : this.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals(tabName)) {
                    // Simulate click
                    for (ActionListener listener : button.getActionListeners()) {
                        listener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null));
                    }
                    break;
                }
            }
        }
    }
}