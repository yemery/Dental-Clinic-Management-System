package org.example.presentation.controller;

import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.service.api.ConsultationService;
import org.example.service.implementation.ConsultationServiceImpl;

import java.util.List;

public class ConsultationController {
    //    private final ConsultationService consultationService= new ConsultationServiceImpl();
    private final ConsultationService consultationService= new ConsultationServiceImpl();

    public List<Consultation> displayAllConsultations(){
        return consultationService.getAllConsultations();
    }

    public void addConsultation(Consultation consultation){ consultationService.addConsultation(consultation);}

    public void updateConsultation(Consultation consultation){
        consultationService.updateConsultation(consultation);
    }

    public void deleteConsultation(Long ID){ consultationService.deleteConsultation(ID);}

    public Consultation getConsultation(Long ID){ return consultationService.getConsultation(ID);}

    public boolean removeIntervention(Consultation consultation,Long ID){
        return consultationService.removeIntervention(consultation,ID);
    }
}
