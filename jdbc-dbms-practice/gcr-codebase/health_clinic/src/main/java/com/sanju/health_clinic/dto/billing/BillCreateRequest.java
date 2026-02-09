package com.sanju.health_clinic.dto.billing;

import java.math.BigDecimal;

public record BillCreateRequest(
    long visitId,
    BigDecimal additionalCharges
) {
}
