package org.example.presentation.view;

import org.example.model.Invoice;
import org.example.model.enums.PaymentType;
import org.example.presentation.controller.InvoiceController;

import java.time.LocalDate;

public class InvoiceView {
    public static void main(String[] args) {
        InvoiceController invC = new InvoiceController();

        Invoice i1 = new Invoice(LocalDate.now(), 14000.0, 8000.4, PaymentType.CASH);
        Invoice i2 = new Invoice(LocalDate.now(), 1500.0, 900.0, PaymentType.CHEQUE);

        invC.addInvoice(i1);
        invC.addInvoice(i2);

        invC.displayAllInvoices();


    }
}
