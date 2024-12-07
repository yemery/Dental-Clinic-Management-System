package org.example.service.api;


import org.example.model.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(Invoice act);
    Invoice getInvoice(Long id);
    Invoice updateInvoice(Invoice act);
    void deleteInvoice(Invoice act);
    List<Invoice> getAllInvoices();
}
