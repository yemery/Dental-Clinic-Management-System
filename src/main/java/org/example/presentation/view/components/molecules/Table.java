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

    public Table(Object[][] data, String[] columnNames, Consumer<Long> editCallback, Consumer<Long> deleteCallback, Consumer<Long> showCallback) {
        super(new DefaultTableModel(data, columnNames));
        setupTable(editCallback, deleteCallback, showCallback);
        adjustTableHeight(data.length);
    }

    public Table(Object[][] data, String[] columnNames) {
        super(new DefaultTableModel(data, columnNames));
        setupTable();
        adjustTableHeight(data.length);
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

    private void setupTable(Consumer<Long> editCallback, Consumer<Long> deleteCallback, Consumer<Long> showCallback) {
        setupTable();

        // Set custom renderer and editor for the "Actions" column
        if (this.getColumn("Actions") != null) {
            this.getColumn("Actions").setCellRenderer(new ButtonPanelRenderer());
            this.getColumn("Actions").setCellEditor(new ButtonPanelEditor(editCallback, deleteCallback, showCallback));
        }
    }

    private void adjustTableHeight(int rowCount) {
        // Dynamically adjust the table's preferred height based on the number of rows
        int tableHeight = this.getRowHeight() * rowCount + this.getTableHeader().getHeight();
        this.setPreferredScrollableViewportSize(new Dimension(this.getPreferredScrollableViewportSize().width, tableHeight));
        this.revalidate();
    }

    // Custom TableCellRenderer to display buttons in a JPanel
    static public class ButtonPanelRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Button editBtn = new Button("Edit");
            Button deleteBtn = new Button("Delete");
            Button showBtn = new Button("Show");

            // Add buttons to the panel
            panel.add(editBtn);
            panel.add(deleteBtn);
            panel.add(showBtn);

            return panel; // Return the panel as renderer
        }
    }

    public static class ButtonPanelEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {
        private JPanel panel;
        private Button editBtn;
        private Button deleteBtn;
        private Button showBtn;
        private JTable table;
        private Long ID;

        private Consumer<Long> editCallback; // Callback for edit action
        private Consumer<Long> deleteCallback; // Callback for delete action
        private Consumer<Long> showCallback;

        public ButtonPanelEditor(Consumer<Long> editCallback, Consumer<Long> deleteCallback, Consumer<Long> showCallback) {
            this.editCallback = editCallback;
            this.deleteCallback = deleteCallback;
            this.showCallback = showCallback;

            panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            editBtn = new Button("Edit");
            deleteBtn = new Button("Delete");
            showBtn = new Button("Show");

            editBtn.addActionListener(this);
            deleteBtn.addActionListener(this);
            showBtn.addActionListener(this);

            panel.add(editBtn);
            panel.add(deleteBtn);
            panel.add(showBtn);
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
            } else if (e.getSource() == showBtn) {
                showCallback.accept(ID);
            }
            fireEditingStopped();
        }
    }
}
