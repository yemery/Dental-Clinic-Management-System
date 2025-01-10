package org.example.service.api;


import org.example.model.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(Invoice act);
    Invoice getInvoice(Long id);
    Invoice updateInvoice(Invoice act);
    void deleteInvoice(Long ID);
    List<Invoice> getAllInvoices();
    public boolean removeInvoiceFromConsultation(Long id);
}
