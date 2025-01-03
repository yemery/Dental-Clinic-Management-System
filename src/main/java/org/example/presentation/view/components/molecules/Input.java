package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Input<T> extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JComboBox<T> comboBox;
    private Class<T> type;

    public Input(String labelText, Class<T> type) {
        this.type = type;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // Create and add label
        label = new JLabel(labelText);
        this.add(label, gbc);

        // Default input field
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 40));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(textField, gbc);
    }

    // Overloaded constructor for JComboBox
    public Input(String labelText, T[] options) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(5, 0, 5, 0);
        gbc.anchor = GridBagConstraints.WEST;

        // Create and add label
        label = new JLabel(labelText);
        this.add(label, gbc);

        // Create combo box
        comboBox = new JComboBox<>(options);
        comboBox.setPreferredSize(new Dimension(300, 40));
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(comboBox, gbc);
    }

    // Get value for both JTextField and JComboBox
    public T getValue() throws IllegalArgumentException {
        if (comboBox != null) {
            return (T) comboBox.getSelectedItem();
        }
        String text = textField.getText();
        try {
            if (type == String.class) {
                return type.cast(text);
            } else if (type == Integer.class) {
                return type.cast(Integer.parseInt(text));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(text));
            } else if (type == java.util.Date.class) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return type.cast(sdf.parse(text));
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type.getName());
            }
        } catch (NumberFormatException | ParseException e) {
            throw new IllegalArgumentException("Invalid input for type " + type.getName() + ": " + text, e);
        }
    }

    // Set value in text field or combo box
    public void setValue(T value) {
        if (comboBox != null) {
            comboBox.setSelectedItem(value);
        } else if (type == java.util.Date.class) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            textField.setText(sdf.format((java.util.Date) value));
        } else {
            textField.setText(value.toString());
        }
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabelText(String labelText) {
        label.setText(labelText);
    }
}
