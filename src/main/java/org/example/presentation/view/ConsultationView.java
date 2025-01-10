package org.example.presentation.view;

import org.example.model.*;
import org.example.model.enums.ActCategory;
import org.example.model.enums.ConsultationType;
import org.example.model.enums.PaymentType;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.ConsultationController;
import org.example.presentation.controller.InterventionController;

import java.time.LocalDate;
import java.util.List;

public class ConsultationView {
    public static void main(String[] args) {
        ConsultationController consultationC = new ConsultationController();

        Consultation c1= new Consultation(ConsultationType.GENERAL_CONSULTATION,"note1");

//        List<Long> interventionList = List.of(
//                11L,65L
//        );
//        c1.setInterventions(interventionList);
//
//
//        Invoice inv1 = new Invoice(LocalDate.now(), 14000.0, 8000.4, PaymentType.CASH);
//        c1.setInvoice(inv1.getId());
//        Prescription p1 = new Prescription();
//        c1.setPrescription(p1.getId());
//
////        PrescriptionMedicine prescrMed = new PrescriptionMedicine(12,12,"desc", new Medicine(82.0, "med 1", "desc 1"));
////        p1.setPrescriptionsMedicine(prescrMed);
//
//        consultationC.addConsultation(c1);
//
//
//        consultationC.displayAllConsultations();

//        System.out.println(c1.getInterventions().size());
//
        InterventionController interventionC = new InterventionController();
        interventionC.deleteIntervention(11L);
//
//        System.out.println(c1.getInterventions().size());

    }
}
