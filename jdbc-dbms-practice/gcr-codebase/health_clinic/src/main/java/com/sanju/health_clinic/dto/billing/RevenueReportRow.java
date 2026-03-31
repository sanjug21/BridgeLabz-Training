package com.sanju.health_clinic.dto.billing;

import java.math.BigDecimal;

public record RevenueReportRow(
    String groupKey,
    BigDecimal totalAmount
) {
}
