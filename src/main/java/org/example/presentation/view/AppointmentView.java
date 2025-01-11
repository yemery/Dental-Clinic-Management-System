package org.example.presentation.view;

import org.example.model.*;
import org.example.model.enums.*;
import org.example.presentation.controller.AppointmentController;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentView {
    public static void main(String[] args) {
        AppointmentController appointmentController = new AppointmentController();


//        Appointment ap1 = new Appointment(LocalTime.now(),LocalDate.now(),AppoitmentType.Control,AppointementStatus.CONFIRMED);
//        Appointment ap2 = new Appointment(LocalTime.now(),LocalDate.now(),AppoitmentType.Advice,AppointementStatus.CANCELLED);

//        appointmentController.addAppointment(ap1);
//        appointmentController.addAppointment(ap2);
//
//        appointmentController.displayAppointments();

//        System.out.println(appointmentController.getAppointment(83L)    );

//        Appointment ap= appointmentController.getAppointment(83L);
//        System.out.println(ap);
//        ap.setConsultations(12L);
//        appointmentController.updateAppointment(ap);
//        System.out.println(appointmentController.getAppointment(83L));
        appointmentController.deleteAppointment(16L);




    }
}
