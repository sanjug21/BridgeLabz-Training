package com.sanju.health_clinic.dto.visit;

import java.time.LocalDate;

public record VisitResponse(
    long id,
    long appointmentId,
    long patientId,
    long doctorId,
    LocalDate visitDate,
    String diagnosis,
    String notes
) {
}
