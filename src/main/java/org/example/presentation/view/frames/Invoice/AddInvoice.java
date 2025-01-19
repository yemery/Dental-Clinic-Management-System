package org.example.presentation.view.frames.Invoice;

import org.example.presentation.view.components.atoms.Button;
import org.example.presentation.view.components.molecules.Input;
import org.example.presentation.view.frames.Frame;
import org.example.presentation.view.layouts.AppLayout;
import org.example.model.Invoice;
import org.example.model.enums.PaymentType;
import org.example.presentation.controller.InvoiceController;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class AddInvoice extends Frame {
    private Input<Double> totalAmount = new Input<>("Total Amount", Double.class);
    private Input<Double> payedAmount = new Input<>("Payed Amount", Double.class);
    private JComboBox<PaymentType> paymentTypeComboBox;

    private AppLayout appLayout;

    public AddInvoice(AppLayout appLayout) {
        super();
        this.appLayout = appLayout;

        // Panel setup
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Adjust borders for better spacing

        // Title label
        JLabel titleLabel = new JLabel("Add New Invoice");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0)); // Add some space below the title
        panel.add(titleLabel);

        // Add input fields
        totalAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
        payedAmount.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(totalAmount);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs
        panel.add(payedAmount);
        panel.add(Box.createRigidArea(new Dimension(0, 15))); // Add spacing between inputs

        // Payment type combo box
        JLabel paymentTypeLabel = new JLabel("Payment Type:");
        paymentTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(paymentTypeLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 5))); // Add spacing before combo box

        paymentTypeComboBox = new JComboBox<>(PaymentType.values());
        paymentTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        paymentTypeComboBox.setPreferredSize(new Dimension(150, 30));
        panel.add(paymentTypeComboBox);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); // Add spacing before the button

        // Submit button
        Button submitBtn = new Button("Submit");
        submitBtn.setPreferredSize(new Dimension(200, 50));
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setFont(new Font("Arial", Font.BOLD, 16)); // Make the button text larger
        submitBtn.addActionListener(e -> saveInvoice()); // Add submit logic
        panel.add(submitBtn);

        // Add panel to frame
        this.add(panel);
        dispose(); // close the frame
        this.setVisible(true);
    }

    private void saveInvoice() {
        try {

            Double totalAmountValue = totalAmount.getValue();
            Double payedAmountValue = payedAmount.getValue();
            if (totalAmountValue == null || payedAmountValue == null) {
                throw new IllegalArgumentException("Both total amount and payed amount are required.");
            }

            if (payedAmountValue > totalAmountValue) {
                throw new IllegalArgumentException("Payed amount cannot exceed total amount.");
            }

            PaymentType paymentType = (PaymentType) paymentTypeComboBox.getSelectedItem();
            if (paymentType == null) {
                throw new IllegalArgumentException("Payment type is required.");
            }

            // Create and save invoice
            InvoiceController invoiceController = new InvoiceController();
            Invoice invoice = new Invoice(totalAmountValue, payedAmountValue, paymentType);
            invoiceController.addInvoice(invoice);

            JOptionPane.showMessageDialog(this, "Invoice saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            appLayout.getNavbar().simulateTabClick("Invoices");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
