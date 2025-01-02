package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;

public class Input extends JPanel {
    private JLabel label;
    private JTextField textField;

    public Input(String labelText) {

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        label = new JLabel(labelText);
        this.add(label, gbc);

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 40));
        this.add(textField, gbc);
    }

    public String getText() {
        return textField.getText();
    }

    public void setText(String text) {
        textField.setText(text);
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
