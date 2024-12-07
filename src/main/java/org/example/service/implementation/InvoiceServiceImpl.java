package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.implementation.InvoiceDaoImpl;
import org.example.model.Invoice;
import org.example.service.api.InvoiceService;

import java.sql.SQLOutput;
import java.util.List;

public class InvoiceServiceImpl implements InvoiceService {
    private final IDao<Invoice, Long> dao = new InvoiceDaoImpl();

    @Override
    public Invoice addInvoice(Invoice invoice) {
        try{
            Invoice i = dao.add(invoice);
            System.out.println("Invoice added successfully");
            return i;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Invoice getInvoice(Long id) {
        try{
            Invoice i = dao.getById(id);
            System.out.println("Invoice Found");
            return i;
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        try {
            dao.update(invoice);
            System.out.println("Invoice updated successfully");
            return invoice;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteInvoice(Invoice invoice) {
        try{
            dao.delete(invoice);
            System.out.println("Invoice deleted successfully");
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Invoice> getAllInvoices() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
