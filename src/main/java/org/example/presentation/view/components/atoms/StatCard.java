package org.example.presentation.view.components.atoms;

import javax.swing.*;
import java.awt.*;

public class StatCard extends JPanel {
    private JLabel label;
    private JLabel value;

    public StatCard(String labelText, Float value) {
        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(0x3730a3));

        this.setPreferredSize(new Dimension(200, 100));
        this.setMinimumSize(new Dimension(200, 100));
        this.setMaximumSize(new Dimension(200, 100));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = GridBagConstraints.RELATIVE;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 5, 0, 5);

        label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        // Format the float value to remove .0 if it's a whole number
        String formattedValue = value % 1 == 0
                ? String.format("%.0f", value)
                : String.valueOf(value);

        this.value = new JLabel(formattedValue);
        this.value.setForeground(Color.WHITE);
        this.value.setFont(new Font("Arial", Font.BOLD, 14));

        this.add(label, gbc);
        this.add(this.value, gbc);
    }
}