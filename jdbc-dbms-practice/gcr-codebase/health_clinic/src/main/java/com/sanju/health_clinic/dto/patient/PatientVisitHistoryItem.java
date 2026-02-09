package com.sanju.health_clinic.dto.patient;

import java.time.LocalDate;

public record PatientVisitHistoryItem(
    long appointmentId,
    LocalDate appointmentDate,
    String appointmentStatus,
    String doctorName,
    LocalDate visitDate,
    String diagnosis
) {
}
