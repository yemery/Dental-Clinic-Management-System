package org.example.presentation.controller;

import org.example.model.Appointment;
import org.example.service.api.AppointmentService;
import org.example.service.implementation.AppointmentServiceImpl;

public class AppointmentController {
    public  final AppointmentService appointmentService = new AppointmentServiceImpl();

    public void addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
    }
    public void displayAppointment() {
        appointmentService.getAppointments().forEach(System.out::println);
    }
    public void updateAppointment(Appointment appointment) {
        appointmentService.updateAppointment(appointment);
    }
    public void deleteAppointment(Appointment appointment) {
        appointmentService.deleteAppointment(appointment);
    }

}
