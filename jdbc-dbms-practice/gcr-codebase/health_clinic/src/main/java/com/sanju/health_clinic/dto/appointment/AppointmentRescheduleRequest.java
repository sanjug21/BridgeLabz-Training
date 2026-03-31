package com.sanju.health_clinic.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentRescheduleRequest(
    long doctorId,
    LocalDate appointmentDate,
    LocalTime appointmentTime
) {
}
