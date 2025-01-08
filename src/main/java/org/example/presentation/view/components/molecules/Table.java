package org.example.presentation.view.components.molecules;

import org.example.presentation.view.components.atoms.Button;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

public class Table extends JTable {

    public Table(Object[][] data, String[] columnNames, Consumer<Long> editCallback, Consumer<Long> deleteCallback) {
        super(new DefaultTableModel(data, columnNames));
        setupTable(editCallback, deleteCallback);
    }

    //    public Table(Object[][] data, String[] columnNames, Consumer<Long> editCallback) {}
    public Table(Object[][] data, String[] columnNames) {
        super(new DefaultTableModel(data, columnNames));
        setupTable();
    }


    private void setupTable() {
        // Prevent cell editing by clicking
        this.setDefaultEditor(Object.class, null);

        this.setRowHeight(50);
        this.setShowGrid(true);
        this.setGridColor(Color.LIGHT_GRAY);
        this.getTableHeader().setBackground(new Color(0x3730a3));
        this.getTableHeader().setForeground(Color.WHITE);
        this.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        this.setFont(new Font("Arial", Font.PLAIN, 12));

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void setupTable(Consumer<Long> editCallback, Consumer<Long> deleteCallback) {
        this.setDefaultEditor(Object.class, null);
        this.setRowHeight(50);
        this.setShowGrid(true);
        this.setGridColor(Color.LIGHT_GRAY);
        this.getTableHeader().setBackground(new Color(0x3730a3));
        this.getTableHeader().setForeground(Color.WHITE);
        this.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        this.setFont(new Font("Arial", Font.PLAIN, 12));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Set custom renderer and editor for the "Actions" column
        if (this.getColumn("Actions") != null) {
            this.getColumn("Actions").setCellRenderer(new ButtonPanelRenderer());
            this.getColumn("Actions").setCellEditor(new ButtonPanelEditor(editCallback, deleteCallback));
        }
    }

    // Custom TableCellRenderer to display buttons in a JPanel
    static public class ButtonPanelRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            org.example.presentation.view.components.atoms.Button editBtn = new org.example.presentation.view.components.atoms.Button("Edit");
            org.example.presentation.view.components.atoms.Button deleteBtn = new org.example.presentation.view.components.atoms.Button("Delete");

            // Add buttons to the panel
            panel.add(editBtn);
            panel.add(deleteBtn);

            return panel; // Return the panel as renderer
        }
    }

    public static class ButtonPanelEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JPanel panel;
        private Button editBtn;
        private Button deleteBtn;
        private JTable table;
        private Long ID;

        private Consumer<Long> editCallback; // Callback for edit action
        private Consumer<Long> deleteCallback; // Callback for delete action

        public ButtonPanelEditor() {
            // Initialize the panel and buttons
            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            editBtn = new org.example.presentation.view.components.atoms.Button("Edit");
            deleteBtn = new Button("Delete");

            // Add action listeners to the buttons
            editBtn.addActionListener(this);
            deleteBtn.addActionListener(this);

            // Add buttons to the panel
            panel.add(editBtn);
            panel.add(deleteBtn);
        }

        public ButtonPanelEditor(Consumer<Long> editCallback, Consumer<Long> deleteCallback) {
            this.editCallback = editCallback;
            this.deleteCallback = deleteCallback;

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            editBtn = new Button("Edit");
            deleteBtn = new Button("Delete");

            editBtn.addActionListener(this);
            deleteBtn.addActionListener(this);

            panel.add(editBtn);
            panel.add(deleteBtn);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.table = table;

            Object idValue = table.getValueAt(row, 0);
            if (idValue instanceof Long) {
                ID = (Long) idValue;
            }

            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == editBtn && ID != null) {
                editCallback.accept(ID); // Trigger the edit callback
            } else if (e.getSource() == deleteBtn) {

                int result = JOptionPane.showConfirmDialog(
                        table, // Parent component for positioning the dialog
                        "Are you sure you want to delete the entry with ID: " + ID + "?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    // Proceed with deletion if user confirms
                    if (deleteCallback != null) {
                        deleteCallback.accept(ID);
                    }
                }
            }
            fireEditingStopped();
        }
    }
}