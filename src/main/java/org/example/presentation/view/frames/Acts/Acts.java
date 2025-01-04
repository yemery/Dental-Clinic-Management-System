package org.example.presentation.view.frames.Acts;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Acts extends JPanel {
    private JPanel container;
    private JPanel addContainer;

    Button addActBtn;
    Table actsTable;

    private AppLayout appLayout;

    public Acts(Object[][] data, AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Set layout for the main panel
        this.setLayout(new BorderLayout());
        this.container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Add "Add New Act" button
        this.addActBtn = new Button("Add New Act");
        addActBtn.addActionListener(e -> new AddAct(appLayout));
        this.add(this.addActBtn);

        // Create button container at the top
        this.addContainer = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.width = getParent().getWidth();
                return dim;
            }
        };
        this.addContainer.add(this.addActBtn, BorderLayout.WEST);
        this.container.add(this.addContainer);

        // Table container
        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"ID", "Name", "Price", "Act Category", "Actions"};
        Object[][] dataWithActions = new Object[data.length][5];

        for (int i = 0; i < dataWithActions.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                dataWithActions[i][j] = data[i][j];
            }

            // Create the last column's data with button panel
            dataWithActions[i][4] = "Actions";
        }

        // Create a table with column data and custom renderer/editor for "Actions"
        this.actsTable = new Table(dataWithActions, columnNames,
                id -> new EditAct(id, appLayout),
                id -> new DeleteAct(id, appLayout)
                );

        // Set custom renderer and editor for the "Actions" column
        this.actsTable.getColumn("Actions").setCellRenderer(new Table.ButtonPanelRenderer());

        // Add the table to the container
        tableContainer.add(new JScrollPane(actsTable), BorderLayout.CENTER);
        this.container.add(tableContainer);

        this.add(this.container, BorderLayout.CENTER);
        this.setVisible(true);
    }


}