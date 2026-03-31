package com.sanju.health_clinic.dto.doctor;

import java.math.BigDecimal;

public record DoctorResponse(
    long id,
    String fullName,
    String phone,
    String email,
    BigDecimal consultationFee,
    long specialtyId,
    String specialtyName,
    boolean active
) {
}
