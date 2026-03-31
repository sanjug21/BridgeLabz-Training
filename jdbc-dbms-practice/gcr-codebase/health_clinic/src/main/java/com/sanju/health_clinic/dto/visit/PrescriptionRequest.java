package com.sanju.health_clinic.dto.visit;

public record PrescriptionRequest(
    String medicineName,
    String dosage,
    Integer durationDays,
    String instructions
) {
}
