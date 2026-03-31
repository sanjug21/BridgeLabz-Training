package com.sanju.health_clinic.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponse(
    long id,
    long patientId,
    long doctorId,
    LocalDate appointmentDate,
    LocalTime appointmentTime,
    String status,
    String notes
) {
}
