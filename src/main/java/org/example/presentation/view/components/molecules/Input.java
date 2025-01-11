package org.example.presentation.view.components.molecules;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Input<T> extends JPanel {
    private JLabel label;
    private JTextField textField;
    private JComboBox<T> comboBox;
    private JSpinner dateSpinner;
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

        // Add input field or spinner depending on type
        if (type == LocalDate.class || type == Date.class) {
            dateSpinner = new JSpinner(new SpinnerDateModel());
            JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
            dateSpinner.setEditor(dateEditor);
            dateSpinner.setPreferredSize(new Dimension(300, 40));
            this.add(dateSpinner, gbc);
        } else {
            textField = new JTextField();
            textField.setPreferredSize(new Dimension(300, 40));
            this.add(textField, gbc);
        }
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
        this.add(comboBox, gbc);
    }

    // Get value for JTextField, JComboBox, or JSpinner
    public T getValue() throws IllegalArgumentException {
        if (comboBox != null) {
            return (T) comboBox.getSelectedItem();
        }
        if (dateSpinner != null) {
            Date selectedDate = (Date) dateSpinner.getValue();
            if (type == LocalDate.class) {
                return type.cast(selectedDate.toInstant()
                        .atZone(java.time.ZoneId.systemDefault())
                        .toLocalDate());
            }
            return type.cast(selectedDate);
        }
        String text = textField.getText();
        try {
            if (type == String.class) {
                return type.cast(text);
            } else if (type == Integer.class) {
                return type.cast(Integer.parseInt(text));
            } else if (type == Double.class) {
                return type.cast(Double.parseDouble(text));
            } else {
                throw new IllegalArgumentException("Unsupported type: " + type.getName());
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input for type " + type.getName() + ": " + text, e);
        }
    }

    // Set value in text field, combo box, or spinner
    public void setValue(T value) {
        if (comboBox != null) {
            comboBox.setSelectedItem(value);
        } else if (dateSpinner != null && value instanceof Date) {
            dateSpinner.setValue(value);
        } else if (dateSpinner != null && value instanceof LocalDate) {
            Date date = java.sql.Date.valueOf((LocalDate) value);
            dateSpinner.setValue(date);
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
