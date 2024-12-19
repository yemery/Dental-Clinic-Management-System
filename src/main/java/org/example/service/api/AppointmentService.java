package org.example.service.api;

import org.example.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment addAppointment(Appointment appointment);
    Appointment getAppointment(Long id);
    Appointment updateAppointment(Appointment appointment);
    void deleteAppointment(Appointment appointment);
    List<Appointment> getAppointments();
}
