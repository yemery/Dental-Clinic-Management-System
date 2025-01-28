package org.example.dao.JsonFileImpl;

import org.example.model.Invoice;

public class InvoiceDaoImpl extends JsonDaoImpl<Invoice,Long>{
    public InvoiceDaoImpl(String fileName) {
        super(fileName, Invoice.class);
    }
}
