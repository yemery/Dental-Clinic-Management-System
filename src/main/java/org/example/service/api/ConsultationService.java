package org.example.service.api;

import org.example.model.Certificate;
import org.example.model.Consultation;
import org.example.model.Intervention;

import java.util.List;

public interface ConsultationService {
    Consultation addConsultation(Consultation consultation);
    Consultation updateConsultation(Consultation consultation);
    Consultation getConsultation(Long ID);
    List<Consultation> getAllConsultations();
    void deleteConsultation(Long ID);

    boolean addIntervention(Consultation consultation, Long interventionID);
    boolean removeIntervention(Consultation consultation, Long interventionID);

    boolean addCertificate(Consultation consultation, Long certificateID);
    boolean removeCertificate(Consultation consultation);

    boolean addInvoice(Consultation consultation, Long invoiceID);

//    would we use remove invoice??
//    boolean removeInvoice(Consultation consultation);

    boolean addPrescription(Consultation consultation, Long prescriptionID);
    boolean removePrescription(Consultation consultation);

    boolean removeConsultationFromAppointment (Long ID);
}
