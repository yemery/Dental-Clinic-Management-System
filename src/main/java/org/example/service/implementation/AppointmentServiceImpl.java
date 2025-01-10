package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.ArrayListImpl.AppointmentDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Appointment;
import org.example.service.api.AppointmentService;

import java.util.List;

public class AppointmentServiceImpl implements AppointmentService {
//    private final IDao<Appointment, Long> dao = new AppointmentDaoImpl();
    private final IDao<Appointment, Long> dao = new JsonDaoImpl<>("Appointement.json",Appointment.class);

    @Override
    public Appointment addAppointment(Appointment appointment) {
        try {
            return dao.add(appointment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment getAppointment(Long id) {
        try {
            return dao.getById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        try {
            return dao.update(appointment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAppointment(Long ID) {
        try {
            dao.delete(ID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getAppointments() {
        try {
            return dao.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
