package org.example.presentation.view.frames.Acts;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Table;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import java.awt.*;

public class Acts extends JPanel{
    private JPanel container;
    private JPanel addContainer;

    Button addActBtn;
    Table actsTable;

    private AppLayout appLayout;

    public Acts(Object[][] data, AppLayout appLayout){
        super();
        this.appLayout = appLayout;

        this.setLayout(new BorderLayout());
        this.container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        this.addActBtn = new Button("Add New Act");
        addActBtn.addActionListener(e -> new AddAct(appLayout));
        this.add(this.addActBtn);

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

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"ID", "Name", "Price", "Act Category", "Actions"};
        this.actsTable = new Table(data, columnNames);
        tableContainer.add(new JScrollPane(actsTable), BorderLayout.CENTER);
        this.container.add(tableContainer);

        this.add(this.container, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
