package com.sanju.health_clinic.dto.billing;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BillResponse(
    long id,
    long visitId,
    BigDecimal totalAmount,
    String paymentStatus,
    LocalDate paymentDate,
    String paymentMode
) {
}
