package org.example.presentation.controller;

import org.example.model.Invoice;
import org.example.service.api.InvoiceService;
import org.example.service.implementation.InvoiceServiceImpl;

import java.util.List;

public class InvoiceController {
    private final InvoiceService invoiceService = new InvoiceServiceImpl();

    public void addInvoice(Invoice i){ invoiceService.addInvoice(i);}
    public List<Invoice> displayAllInvoices(){return invoiceService.getAllInvoices();}
    public void updateInvoice(Invoice i){invoiceService.updateInvoice(i);}
    public void deleteInvoice(Long ID){invoiceService.deleteInvoice(ID);}
    public Invoice getInvoice(Long ID){return invoiceService.getInvoice(ID);}
}
