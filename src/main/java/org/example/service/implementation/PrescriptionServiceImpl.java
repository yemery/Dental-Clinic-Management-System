package org.example.service.implementation;

import org.example.dao.JsonFileImpl.PrescriptionDaoImpl;
import org.example.model.Consultation;
import org.example.model.Prescription;
import org.example.service.api.ConsultationService;
import org.example.service.api.PrescriptionService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PrescriptionServiceImpl implements PrescriptionService {
    public final PrescriptionDaoImpl dao = new PrescriptionDaoImpl("Prescriptions.json");

    @Override
    public Prescription getPrescriptionById(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
        try{
            return dao.add(prescription);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Prescription updatePrescription(Prescription prescription) {
        try{
            return dao.update(prescription);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePrescription(Long ID) {
        try{
            dao.delete(ID);
            this.deleteFromConsultation(ID);
            System.out.println("Prescription deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        try{
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteFromConsultation(Long ID) {
        ConsultationService consultationsService = new ConsultationServiceImpl();
        List<Consultation> consultations = consultationsService.getAllConsultations();

        AtomicBoolean updated = new AtomicBoolean(false);
        consultations.stream().filter(consultation -> consultation.getPrescription().equals(ID))
                .forEach(consultation -> {
                    System.out.println(consultation);
                    consultation.setPrescription(0L);
                    System.out.println(consultation);
                    consultationsService.updateConsultation(consultation);
                    updated.set(true);
                });

        return updated.get();
    }
}
