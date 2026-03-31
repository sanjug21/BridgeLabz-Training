package com.sanju.health_clinic.dto.visit;

import java.time.LocalDate;

public record MedicalHistoryItem(
    long visitId,
    LocalDate visitDate,
    String diagnosis,
    String appointmentStatus,
    String doctorName,
    String prescriptionSummary
) {
}
