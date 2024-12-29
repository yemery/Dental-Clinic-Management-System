package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Intervention;
import org.example.model.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImpl implements IDao<Invoice,Long> {
   private final List<Invoice> invoices =  new ArrayList<Invoice>();
    @Override
    public List<Invoice> getAll() throws Exception {
        if (invoices.isEmpty()) throw new Exception("Invoice list is empty");
        return invoices;
    }

    @Override
    public Invoice getById(Long ID) throws Exception {
        return invoices.stream()
                .filter(i -> ID.equals(i.getId()))
                .findFirst()
                .orElseThrow(()-> new Exception("Invoice not found"));
    }

    @Override
    public Invoice add(Invoice invoice) throws Exception {
        if(invoice == null) throw new Exception("Invoice is null");
        invoices.add(invoice);
        return invoice;
    }

    @Override
    public Invoice update(Invoice invoice) throws Exception {
        Invoice record = this.getById(invoice.getId());
        record.setDate(invoice.getDate());
        record.setType(invoice.getType());
        record.setPayedAmount(invoice.getPayedAmount());
        record.setTotalAmount(invoice.getTotalAmount());
        return record;
    }

    @Override
    public void delete(Long ID) throws Exception {
        Invoice existingInvoice = this.getById(ID);

        invoices.remove(existingInvoice);
    }
}
