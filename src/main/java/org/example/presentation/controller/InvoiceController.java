package org.example.presentation.controller;

import org.example.model.Invoice;
import org.example.service.api.InvoiceService;
import org.example.service.implementation.InvoiceServiceImpl;

public class InvoiceController {
    private final InvoiceService invoiceService = new InvoiceServiceImpl();

    public void addInvoice(Invoice i){ invoiceService.addInvoice(i);}
    public void displayAllInvoices(){invoiceService.getAllInvoices().forEach(System.out::println);}
    public void updateInvoice(Invoice i){invoiceService.updateInvoice(i);}
    public void deleteInvoice(Invoice i){invoiceService.deleteInvoice(i);}

}
