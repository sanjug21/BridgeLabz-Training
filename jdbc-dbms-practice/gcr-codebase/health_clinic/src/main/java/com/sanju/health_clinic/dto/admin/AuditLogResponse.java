package com.sanju.health_clinic.dto.admin;

import java.time.LocalDateTime;

public record AuditLogResponse(
    long id,
    String tableName,
    String action,
    long rowId,
    LocalDateTime changedAt
) {
}
