package org.example.presentation.records;

import org.example.model.enums.AppointementStatus;
import org.example.model.enums.AppoitmentType;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentPatientInfo(
        Long appointmentId,
        LocalTime time,
        LocalDate date,
        String patientFullName,
        AppoitmentType type,
        AppointementStatus status
) {
}
