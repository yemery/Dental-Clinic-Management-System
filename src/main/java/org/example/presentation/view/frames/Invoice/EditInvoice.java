package org.example.presentation.view.frames.Invoice;

import org.example.model.Invoice;
import org.example.model.enums.PaymentType;
import org.example.presentation.controller.InvoiceController;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EditInvoice extends Frame {
    private AppLayout appLayout;
    private Invoice invoice;
    private InvoiceController controller;

    public EditInvoice(Long id, AppLayout layout) {
        super();
        this.appLayout = layout;
        this.controller = new InvoiceController();

        this.invoice = controller.getInvoice(id);

        if (invoice == null) {
            JOptionPane.showMessageDialog(this, "Invoice not found!");
            this.dispose();
            return;
        }

        setupUI();
        this.setVisible(true);
    }

    private void setupUI() {
        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Total Amount field
        JLabel totalAmountLabel = new JLabel("Total Amount:");
        JTextField totalAmountField = new JTextField(invoice.getTotalAmount().toString());

        // Payed Amount field
        JLabel payedAmountLabel = new JLabel("Payed Amount:");
        JTextField payedAmountField = new JTextField(invoice.getPayedAmount().toString());

        // Payment Type combo box
        JLabel paymentTypeLabel = new JLabel("Payment Type:");
        JComboBox<PaymentType> paymentTypeComboBox = new JComboBox<>(PaymentType.values());
        paymentTypeComboBox.setSelectedItem(invoice.getType());

        // Save button
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> {
            try {
                Double totalAmountValue = Double.parseDouble(totalAmountField.getText());
                Double payedAmountValue = Double.parseDouble(payedAmountField.getText());
                if (payedAmountValue > totalAmountValue) {
                    throw new IllegalArgumentException("Payed amount cannot exceed total amount.");
                }

                PaymentType paymentType = (PaymentType) paymentTypeComboBox.getSelectedItem();
                if (paymentType == null) {
                    throw new IllegalArgumentException("Payment type is required.");
                }

                invoice.setTotalAmount(totalAmountValue);
                invoice.setPayedAmount(payedAmountValue);
                invoice.setType(paymentType);

                controller.updateInvoice(invoice);
                JOptionPane.showMessageDialog(this, "Invoice updated successfully!");
                this.dispose();
                appLayout.getNavbar().simulateTabClick("Invoices");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error updating invoice: " + ex.getMessage());
            }
        });

        // Add components to panel
        mainPanel.add(totalAmountLabel);
        mainPanel.add(totalAmountField);
        mainPanel.add(payedAmountLabel);
        mainPanel.add(payedAmountField);
        mainPanel.add(paymentTypeLabel);
        mainPanel.add(paymentTypeComboBox);
        mainPanel.add(new JLabel()); // Spacing
        mainPanel.add(saveButton);

        this.add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        dispose();
        appLayout.getNavbar().simulateTabClick("Invoices");
    }
}
