package org.example.presentation.view.frames;

import org.example.presentation.view.components.atoms.StatCard;
import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {
    private JPanel statsContainer;
    StatCard income;
    StatCard unpaidClients;
    StatCard unpaidInvoices;


    public Dashboard(){
        super();
        setLayout(new BorderLayout()); // to have the full width

        statsContainer = new JPanel() {
            // Override getPreferredSize to ensure full width
            @Override
            public Dimension getPreferredSize() {
                Dimension dim = super.getPreferredSize();
                dim.width = getParent().getWidth(); // Make it full width
                return dim;
            }
        };
        statsContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 50, 0);
        statsContainer.setLayout(flowLayout);

        this.income = new StatCard("month's income", 1000F);
        this.unpaidClients = new StatCard("unpaid clients", 5F);
        this.unpaidInvoices = new StatCard("unpaid invoices", 25F);

        statsContainer.add(income);
        statsContainer.add(unpaidClients);
        statsContainer.add(unpaidInvoices);
        this.add(statsContainer);
        this.setVisible(true);

    }

}
