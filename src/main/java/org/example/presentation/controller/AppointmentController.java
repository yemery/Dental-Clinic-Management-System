package org.example.presentation.controller;

import org.example.model.Appointment;
import org.example.service.api.AppointmentService;
import org.example.service.implementation.AppointmentServiceImpl;

import java.util.List;

public class AppointmentController {
    public  final AppointmentService appointmentService = new AppointmentServiceImpl();

    public void addAppointment(Appointment appointment) {
        appointmentService.addAppointment(appointment);
    }
    public List<Appointment> displayAppointments() {
        return appointmentService.getAppointments();
    }
    public void updateAppointment(Appointment appointment) {
        appointmentService.updateAppointment(appointment);
    }
    public void deleteAppointment(Long ID) {
        appointmentService.deleteAppointment(ID);
    }
    public Appointment getAppointment(Long ID) {
        return appointmentService.getAppointment(ID);
    }

}
