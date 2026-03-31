package com.sanju.health_clinic.dto.billing;

import java.math.BigDecimal;

public record OutstandingBillSummary(
    long patientId,
    String patientName,
    long unpaidCount,
    BigDecimal totalOutstanding
) {
}
