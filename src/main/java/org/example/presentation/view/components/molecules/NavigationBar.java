package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class NavigationBar extends JPanel {
    private JButton selectedButton = null;
    private Color defaultBgColor = new Color(0x3730a3);
    private Color selectedBgColor = new Color(143, 143, 246);
    private Color hoverBgColor = new Color(0x9891F1);

    private int maxVisibleTabs = 10;
    private List<JButton> tabButtons = new ArrayList<>();
    private JMenu overflowMenu;

    public NavigationBar(String... tabs) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        setBackground(defaultBgColor);

        JPopupMenu overflowPopup = new JPopupMenu();
        overflowMenu = new JMenu("More");
        overflowMenu.setForeground(Color.WHITE);
        overflowMenu.setOpaque(true);
        overflowMenu.setBackground(defaultBgColor);
        overflowPopup.add(overflowMenu);

        for (String tabName : tabs) {
            JButton tabButton = createTabButton(tabName);
            tabButtons.add(tabButton);

            if (tabButtons.size() <= maxVisibleTabs) {
                add(tabButton);
            } else {
                JMenuItem overflowItem = new JMenuItem(tabName);
                overflowItem.setOpaque(true);
                overflowItem.setBackground(defaultBgColor);
                overflowItem.setForeground(Color.WHITE);
                overflowItem.addActionListener(e -> simulateTabClick(tabName));
                overflowMenu.add(overflowItem);
            }

            // Set Dashboard as default selected
            if (tabName.equals("Dashboard")) {
                selectedButton = tabButton;
                tabButton.setBackground(selectedBgColor);
            }
        }

        // Add overflow menu if necessary
        if (tabButtons.size() > maxVisibleTabs) {
            JButton moreButton = createTabButton("More");
            moreButton.addActionListener(e -> overflowPopup.show(moreButton, moreButton.getWidth(), 0));
            add(moreButton);
        }
    }

    private JButton createTabButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBackground(defaultBgColor);
        button.setForeground(Color.WHITE);
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
        for (JButton button : tabButtons) {
            if (tabName.equals(button.getText())) {
                button.addActionListener(listener);
                return;
            }
        }
        for (Component component : overflowMenu.getMenuComponents()) {
            if (component instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) component;
                if (tabName.equals(menuItem.getText())) {
                    menuItem.addActionListener(listener);
                }
            }
        }
    }

    public void simulateTabClick(String tabName) {
        for (JButton button : tabButtons) {
            if (tabName.equals(button.getText())) {
                for (ActionListener listener : button.getActionListeners()) {
                    listener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, null));
                }
                return;
            }
        }
        for (Component component : overflowMenu.getMenuComponents()) {
            if (component instanceof JMenuItem) {
                JMenuItem menuItem = (JMenuItem) component;
                if (tabName.equals(menuItem.getText())) {
                    for (ActionListener listener : menuItem.getActionListeners()) {
                        listener.actionPerformed(new ActionEvent(menuItem, ActionEvent.ACTION_PERFORMED, null));
                    }
                }
            }
        }
    }
}
