package org.example.dao.JsonFileImpl;

import org.example.model.Appointment;

public class AppointmentDaoImpl extends JsonDaoImpl<Appointment,Long>{

    public AppointmentDaoImpl(String fileName) {
        super(fileName,Appointment.class);
    }
}
