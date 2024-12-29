package org.example.presentation.controller;

import org.example.model.Consultation;
import org.example.model.Intervention;
import org.example.service.api.ConsultationService;
import org.example.service.implementation.ConsultationServiceImpl;

public class ConsultationController {
    private final ConsultationService consultationService= new ConsultationServiceImpl();

    public void displayAllConsultations(){
        consultationService.getAllConsultations().forEach(System.out::println);
    }

    public void addConsultation(Consultation consultation){ consultationService.addConsultation(consultation);}

    public void updateConsultation(Consultation consultation){
        consultationService.updateConsultation(consultation);
    }

    public void deleteConsultation(Long ID){ consultationService.deleteConsultation(ID);}

//    public void removeIntervention(Consultation consultation, Intervention intervention){
//        consultationService.getConsultation(consultation.getId()).removeIntervention(intervention);
//    }
    public boolean removeIntervention(Consultation consultation,Intervention intervention){
        return consultationService.removeIntervention(consultation,intervention);
    }
}
