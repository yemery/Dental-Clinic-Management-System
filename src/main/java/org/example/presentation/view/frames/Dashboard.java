package org.example.presentation.view.frames;

import org.example.presentation.view.components.atoms.StatCard;
import org.example.presentation.view.components.molecules.Table;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    private JPanel statsContainer;
    private JPanel mainContainer;
    StatCard income;
    StatCard unpaidClients;
    StatCard unpaidInvoices;
    Table table;

    public Dashboard(Object[][] data) {
        super();
        setLayout(new BorderLayout()); // to have the full width

        mainContainer = new JPanel();
        mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.Y_AXIS));

        this.statsContainer = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.width = getParent().getWidth();
                return dim;
            }
        };
        statsContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 50, 0);
        statsContainer.setLayout(flowLayout);
        statsContainer.setBackground(getBackground());

        JPanel tableContainer = new JPanel(new BorderLayout());
        tableContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        this.income = new StatCard("month's income", 1000F);
        this.unpaidClients = new StatCard("unpaid clients", 5F);
        this.unpaidInvoices = new StatCard("unpaid invoices", 25F);

        statsContainer.add(income);
        statsContainer.add(unpaidClients);
        statsContainer.add(unpaidInvoices);
        mainContainer.add(statsContainer);

        String[] cols = {"patient", "hour", "status", "reason"};
        this.table = new Table(data, cols);
        tableContainer.add(new JScrollPane(table), BorderLayout.CENTER);
        mainContainer.add(tableContainer);

        this.add(mainContainer, BorderLayout.CENTER);
        this.setVisible(true);
    }
}