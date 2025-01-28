package org.example.service.implementation;

import org.example.dao.IDao;
import org.example.dao.JsonFileImpl.AppointmentDaoImpl;
import org.example.dao.JsonFileImpl.JsonDaoImpl;
import org.example.model.Appointment;
import org.example.model.MedicalCase;
import org.example.service.api.AppointmentService;
import org.example.service.api.MedicalCaseService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class AppointmentServiceImpl implements AppointmentService {
//    private final IDao<Appointment, Long> dao = new AppointmentDaoImpl();
// private final IDao<Appointment, Long> dao = new JsonDaoImpl<>("Appointement.json",Appointment.class);
    private final AppointmentDaoImpl dao = new AppointmentDaoImpl("Appointement.json");

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
            this.removeAppointmentFromMc(ID);
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

    @Override
    public boolean removeAppointmentFromMc(Long ID) {
        MedicalCaseService medicalCaseService= new MedicalCaseImpl();
        List<MedicalCase> medicalCaseList= medicalCaseService.getAllMedicalCases();

        AtomicBoolean updated = new AtomicBoolean(false);
        medicalCaseList.stream().filter(mc -> mc.getAppointments().contains(ID))
                .forEach(pm -> {
                    pm.getAppointments().remove(ID);
                    medicalCaseService.updateMedicalCase(pm);
                    updated.set(true);
                });

        return updated.get();
    }
}
