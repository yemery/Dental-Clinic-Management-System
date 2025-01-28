package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.InvoiceDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Consultation;
import org.example.model.Invoice;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.ConsultationService;
import org.example.service.api.InvoiceService;
import org.example.service.api.PrescriptionMedicineService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class InvoiceServiceImpl implements InvoiceService {
//    private final IDao<Invoice, Long> dao = new InvoiceDaoImpl();
//private final IDao<Invoice, Long> dao = new JsonDaoImpl<>("Invoice.json", Invoice.class);
    private final InvoiceDaoImpl dao = new InvoiceDaoImpl("Invoice.json");

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
    public void deleteInvoice(Long ID) {
        try{
            dao.delete(ID);
            this.removeInvoiceFromConsultation(ID);
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

    @Override
    public boolean removeInvoiceFromConsultation(Long ID) {
        ConsultationService consultationService= new ConsultationServiceImpl();
        List< Consultation> consultationList= consultationService.getAllConsultations();

        AtomicBoolean updated = new AtomicBoolean(false);
        consultationList.stream().filter(c -> c.getInvoice().equals(ID))
                .forEach(c -> {

                    c.setInvoice(0L);
                    consultationService.updateConsultation(c);
                    updated.set(true);
                });

        return updated.get();
    }
}
