package org.example.presentation.view;

import org.example.model.*;
import org.example.model.enums.*;
import org.example.presentation.controller.MedicalCaseController;

import java.time.LocalDate;
import java.time.LocalTime;

public class MedicalCaseView {
    public static void main(String[] args) {
        MedicalCaseController medicalCaseController = new MedicalCaseController();
        Patient pt1= new Patient("Reg1", Mutuelle.CIMR,"Job1");

        Act a1 = new Act("Act1",2000, ActCategory.CARE_OF_CAVITIES);
        Act a2 = new Act("Act2",2000, ActCategory.DENTAL_PROSTHESES);
        Act a3 = new Act("Act3",2000, ActCategory.IMPLANTOLOGY);
        Act a4 = new Act("Act4",2000, ActCategory.DIAGNOSIS);

        Intervention i1 = new Intervention(900);
        Intervention i2 = new Intervention(90);
//        i1.setActs(a1);
//        i1.setActs(a2);
//        i1.setActs(a3);
//        i1.setActs(a4);
//
//        i2.setActs(a1);
//        i2.setActs(a2);
//        i2.setActs(a3);
//        i2.setActs(a4);


//        Prescription p1 = new Prescription();
//        // then we made a prescriptionMedicine to fill the list
//        PrescriptionMedicine prescrMed = new PrescriptionMedicine(12,12,"desc", new Medicine(82.0, "med 1", "desc 1"));
//        // we added it
//        p1.setPrescriptionsMedicine(prescrMed);
//
//        Invoice inv1 = new Invoice(LocalDate.now(), 14000.0, 8000.4, PaymentType.CASH);
//        Invoice inv2 = new Invoice(LocalDate.now(), 1500.0, 900.0, PaymentType.CHEQUE);
//
//        Consultation c1= new Consultation(ConsultationType.GENERAL_CONSULTATION,"note1", LocalDate.now(), inv1,p1);
//        Consultation c2= new Consultation(ConsultationType.EMERGENCY,"note2", LocalDate.now(), inv2,p1);
//
////        c1.setInterventions(i1);
////        c2.setInterventions(i2);
//
//        Appointment ap1= new Appointment(LocalTime.now(), LocalDate.now(), AppoitmentType.Control,AppointementStatus.CONFIRMED);
//        Appointment ap2= new Appointment(LocalTime.now(), LocalDate.now(), AppoitmentType.Control,AppointementStatus.CONFIRMED);
//
//        MedicalHistory mh1 = new MedicalHistory("label 1", "desc 1", CategoryMedicalHistory.ALLERGY, Risk.MEDIUM);
//        MedicalHistory mh2 = new MedicalHistory("label 2", "desc 2", CategoryMedicalHistory.CHRONIC_DISEASE, Risk.HIGH);
//        MedicalHistory mh3 = new MedicalHistory("label 3", "desc 3", CategoryMedicalHistory.HEREDITARY_DISEASE, Risk.MEDIUM);
//        MedicalHistory mh4 = new MedicalHistory("label 4", "desc 4", CategoryMedicalHistory.OTHER, Risk.LOW);
//
//
//        ap1.setConsultations(c1);
//        ap1.setConsultations(c2);
//
//        ap2.setConsultations(c1);
//        ap2.setConsultations(c2);
//
//        MedicalCase mc1 = new MedicalCase();
//        mc1.setPatient(pt1);
//        mc1.setCreationDate(LocalDate.now());
//        mc1.setAppointments(ap1);
//        mc1.setAppointments(ap2);
//        mc1.setMedicalHistories(mh1);
//        mc1.setMedicalHistories(mh2);
//        mc1.setMedicalHistories(mh3);
//        mc1.setMedicalHistories(mh4);
//        medicalCaseController.addMedicalCase(mc1);
////        medicalCaseController.getAllMedicalCase(mc1.getId());
//       medicalCaseController.getAllMedicalCase();
////        medicalCaseController.deleteMedicalCase(mc1);
////        medicalCaseController.addMedicalCase(mc1);
//
//
//        System.out.println(mc1.getAppointments().size());
//        medicalCaseController.removeAppointment(mc1, ap2);
//        System.out.println(mc1.getAppointments().size());
}
}
