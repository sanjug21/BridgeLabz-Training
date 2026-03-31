package com.sanju.health_clinic.dto.patient;

import java.time.LocalDate;

public record PatientResponse(
    long id,
    String fullName,
    LocalDate dob,
    String phone,
    String email,
    String address,
    String bloodGroup
) {
}
