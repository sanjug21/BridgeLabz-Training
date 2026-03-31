package com.sanju.health_clinic.dto.patient;

import java.time.LocalDate;

public record PatientCreateRequest(
    String fullName,
    LocalDate dob,
    String phone,
    String email,
    String address,
    String bloodGroup
) {
}
