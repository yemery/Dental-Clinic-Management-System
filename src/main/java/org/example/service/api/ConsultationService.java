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
    void deleteConsultation(Consultation consultation);
    public boolean removeIntervention(Consultation consultation, Intervention intervention);
}
