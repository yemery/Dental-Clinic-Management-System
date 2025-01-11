package org.example.presentation.view.frames.Invoice;

import org.example.model.Invoice;
import org.example.presentation.controller.InvoiceController;
import org.example.presentation.view.frames.Frame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ShowInvoice extends Frame {
    public ShowInvoice(Long invoiceId) {
        super();

        InvoiceController controller = new InvoiceController();
        Invoice invoice = controller.getInvoice(invoiceId);

        if (invoice == null) {
            JOptionPane.showMessageDialog(this, "Invoice not found!");
            this.dispose();
            return;
        }

        // Frame setup
        setTitle("Invoice Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close this frame only
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Invoice details panel
        JPanel invoiceDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        invoiceDetailsPanel.setBorder(BorderFactory.createTitledBorder("Invoice Details"));

        invoiceDetailsPanel.add(new JLabel("ID:"));
        invoiceDetailsPanel.add(new JLabel(String.valueOf(invoice.getId())));

        invoiceDetailsPanel.add(new JLabel("Total Amount:"));
        invoiceDetailsPanel.add(new JLabel(String.valueOf(invoice.getTotalAmount())));

        invoiceDetailsPanel.add(new JLabel("Payed Amount:"));
        invoiceDetailsPanel.add(new JLabel(String.valueOf(invoice.getPayedAmount())));

        invoiceDetailsPanel.add(new JLabel("Payment Type:"));
        invoiceDetailsPanel.add(new JLabel(invoice.getType().toString()));

        mainPanel.add(invoiceDetailsPanel);

        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }
}
