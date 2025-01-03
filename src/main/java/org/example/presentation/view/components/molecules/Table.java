package org.example.presentation.view.components.molecules;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Table extends JTable {

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
}