package org.example.presentation.view;

import org.example.model.*;
import org.example.model.enums.ActCategory;
import org.example.model.enums.ConsultationType;
import org.example.model.enums.PaymentType;
import org.example.presentation.controller.ActController;
import org.example.presentation.controller.CertificateController;
import org.example.presentation.controller.ConsultationController;
import org.example.presentation.controller.InterventionController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultationView {
    public static void main(String[] args) {
        ConsultationController consultationC = new ConsultationController();
        CertificateController certificateC = new CertificateController();
//        Consultation c1 = new Consultation(ConsultationType.GENERAL_CONSULTATION, "note1");
//
//        List<Long> interventionList = List.of(
//                11L,65L
//        );
//        c1.setInterventions(interventionList);

//        Invoice inv1 = new Invoice(LocalDate.now(), 14000.0, 8000.4, PaymentType.CASH);
//        c1.setInvoice(inv1.getId());
//        Prescription p1 = new Prescription();
//        c1.setPrescription(p1.getId());
//
////        PrescriptionMedicine prescrMed = new PrescriptionMedicine(12,12,"desc", new Medicine(82.0, "med 1", "desc 1"));
////        p1.setPrescriptionsMedicine(prescrMed);
//
//        Certificate c1 = new Certificate("Reason1", LocalDate.of(2000,2,12),LocalDate.of(2000,3,12));
//        certificateC.addNewCertificate(c1);
//
//        Consultation con1=consultationC.getConsultation(1L);
//        con1.setCertificate(c1.getId());
//        consultationC.updateConsultation(con1);

//
//
//        consultationC.displayAllConsultations();
//
//        System.out.println(c1.getInterventions().size());
//
//        InterventionController interventionC = new InterventionController();
//        interventionC.deleteIntervention(11L);

//        Intervention in = interventionC.getIntervention(11L);
//        List<Long> actsL = List.of(
//                4L, 5L
//        );
//        in.setActs(actsL);
//        interventionC.updateIntervention(in);

//        List<Consultation> cs = consultationC.displayAllConsultations();
//
//        cs.forEach(System.out::println);
//        Consultation cu = consultationC.getConsultation(3L);
//        cu.setInterventions(List.of(
//                33L,7L
//        ));
//        cc.setInterventions(new ArrayList<>());
//        consultationC.updateConsultation(cu);

//        System.out.println(c1.getInterventions().size());

    }
}
