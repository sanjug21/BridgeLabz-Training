package com.sanju.health_clinic.dto.billing;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PaymentRequest(
    BigDecimal amount,
    LocalDate paymentDate,
    String paymentMode,
    String referenceNo
) {
}
