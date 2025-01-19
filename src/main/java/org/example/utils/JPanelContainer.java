package org.example.utils;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class JPanelContainer extends JPanel {
    private JPanel container;
    private JPanel addContainer;

    Button addBtn;
    protected Table dataTable;

    private AppLayout appLayout;

    public JPanelContainer(
            Object[][] data, AppLayout appLayout, String addBtnLabel, String[] colNames, ActionListener add
    ) {
        super();
        this.appLayout = appLayout;

        this.setLayout(new BorderLayout());
        this.container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        this.addBtn = new Button(addBtnLabel);
        this.addBtn.addActionListener(add);

        this.add(this.addBtn);

        this.addContainer = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.width = getParent().getWidth();
                return dim;
            }
        };
        this.addContainer.add(this.addBtn, BorderLayout.WEST);
        this.container.add(this.addContainer);

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = colNames;
        Object[][] dataWithActions = new Object[data.length][columnNames.length];
        this.dataTable = new Table(data, columnNames,
                id -> onEdit(id , appLayout),
                id -> onDelete(id , appLayout),
                id -> onShow(id)
        );

        this.dataTable.getColumn("Actions").setCellRenderer(new Table.ButtonPanelRenderer());
        tableContainer.add(new JScrollPane(dataTable), BorderLayout.CENTER);
        this.container.add(tableContainer);

        this.add(this.container, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // Update methods to accept Long
    protected abstract void onEdit(Long id , AppLayout appLayout);
    protected abstract void onDelete(Long id , AppLayout appLayout);
    protected abstract void onShow(Long id);
}
