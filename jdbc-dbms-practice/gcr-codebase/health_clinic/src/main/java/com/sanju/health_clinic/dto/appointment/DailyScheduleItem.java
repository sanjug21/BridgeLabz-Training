package com.sanju.health_clinic.dto.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record DailyScheduleItem(
    long appointmentId,
    LocalDate appointmentDate,
    LocalTime appointmentTime,
    String patientName,
    String doctorName,
    String status
) {
}
