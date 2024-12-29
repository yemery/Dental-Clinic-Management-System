package org.example.dao.ArrayListImpl;

import org.example.dao.IDao;
import org.example.model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentDaoImpl implements IDao<Appointment,Long> {
    private final List<Appointment> appointments = new ArrayList<>();
    @Override
    public List<Appointment> getAll() throws Exception {
        if (!appointments.isEmpty()){
            return appointments;
        }
        throw new Exception("No Appointements found");
    }

    @Override
    public Appointment getById(Long ID) throws Exception {
        return appointments.stream().filter(
                appointment -> ID.equals(appointment.getId())
        ).findFirst().orElseThrow(( ) -> new Exception("No Appointement found"));
    }

    @Override
    public Appointment add(Appointment appointment) throws Exception {
        if (appointment != null) {
            appointments.add(appointment);
            return appointment;
        }
        throw new Exception("No Appointements been added");
    }

    @Override
    public Appointment update(Appointment appointment) throws Exception {
        Appointment existingAppointment = getById(appointment.getId());
        existingAppointment.setStatus(appointment.getStatus());
        existingAppointment.setType(appointment.getType()   );
        existingAppointment.setDate(appointment.getDate());
        existingAppointment.setTime(appointment.getTime());
        return existingAppointment;
    }

    @Override
    public void delete(Long ID) throws Exception {
        Appointment existingAppointment = this.getById(ID);

        appointments.remove(existingAppointment);
    }
}
