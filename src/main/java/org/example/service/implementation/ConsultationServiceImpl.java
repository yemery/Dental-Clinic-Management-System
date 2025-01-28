package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.ConsultationDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Appointment;
import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.model.PrescriptionMedicine;
import org.example.service.api.AppointmentService;
import org.example.service.api.ConsultationService;
import org.example.service.api.PrescriptionMedicineService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConsultationServiceImpl implements ConsultationService {
//    private final IDao<Consultation, Long> dao = new ConsultationDaoImpl();
// private final IDao<Consultation, Long> dao = new JsonDaoImpl<>("Consultations.json",Consultation.class);
    private final ConsultationDaoImpl dao = new ConsultationDaoImpl("Consultations.json");

    @Override
    public Consultation addConsultation(Consultation consultation) {
        try{

            return dao.add(consultation);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation updateConsultation(Consultation consultation) {
        try{
            return dao.update(consultation);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Consultation getConsultation(Long ID) {
        try{
            return dao.getById(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> getAllConsultations() {
        try{
            return dao.getAll();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Long ID) {
        try{
            dao.delete(ID);
            this.removeConsultationFromAppointment(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addIntervention(Consultation consultation, Long interventionID) {
        try{
            List<Long> interventions = consultation.getInterventions();
            interventions.add(interventionID);
            consultation.setInterventions(interventions);
            return true;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeIntervention(Consultation consultation, Long ID) {
        try{
            Consultation c = dao.getById(consultation.getId());
            List<Long> interventions = c.getInterventions();
            c.getInterventions().remove(ID);
            c.setInterventions(interventions);
            this.updateConsultation(consultation);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCertificate(Consultation consultation, Long certificateID) {
        try{
            consultation.setCertificate(certificateID);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeCertificate(Consultation consultation) {
        try{
            consultation.setCertificate(null);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addInvoice(Consultation consultation, Long invoiceID) {
        try{
            consultation.setInvoice(invoiceID);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addPrescription(Consultation consultation, Long prescriptionID) {
        try{
            consultation.setPrescription(prescriptionID);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removePrescription(Consultation consultation) {
        try{
            consultation.setPrescription(null);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean removeConsultationFromAppointment(Long ID) {
        AppointmentService appointmentService = new AppointmentServiceImpl();
        List<Appointment> appointmentList = appointmentService.getAppointments();

        AtomicBoolean updated = new AtomicBoolean(false);
        appointmentList.stream().filter(ap -> ap.getConsultation().equals(ID))
                .forEach(a -> {
                    a.setConsultations(0L);
                    appointmentService.updateAppointment(a);
                    updated.set(true);
                });

        return updated.get();
    }
}
