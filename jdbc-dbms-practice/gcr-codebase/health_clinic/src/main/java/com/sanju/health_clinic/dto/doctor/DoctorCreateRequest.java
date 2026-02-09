package com.sanju.health_clinic.dto.doctor;

import java.math.BigDecimal;

public record DoctorCreateRequest(
    String fullName,
    String phone,
    String email,
    BigDecimal consultationFee,
    long specialtyId
) {
}
