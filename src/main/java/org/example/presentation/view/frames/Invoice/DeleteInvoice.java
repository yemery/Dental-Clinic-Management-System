package org.example.presentation.view.frames.Invoice;

import org.example.presentation.controller.InvoiceController;
import org.example.presentation.view.layouts.AppLayout;

public class DeleteInvoice {
    public DeleteInvoice(Long id, AppLayout appLayout) {
        InvoiceController controller = new InvoiceController();
        controller.deleteInvoice(id);

        appLayout.getNavbar().simulateTabClick("Invoices");
    }
}