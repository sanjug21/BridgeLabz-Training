package com.sanju.health_clinic.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.admin.AuditLogResponse;

@Repository
public class AuditRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AuditRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AuditLogResponse> findAuditLogs(String tableName) {
        String sql = """
            SELECT id, table_name, action, row_id, changed_at
            FROM audit_log
            WHERE (:tableName IS NULL OR table_name = :tableName)
            ORDER BY changed_at DESC
            """;
        return jdbcTemplate.query(sql, Map.of("tableName", tableName), (rs, rowNum) -> new AuditLogResponse(
            rs.getLong("id"),
            rs.getString("table_name"),
            rs.getString("action"),
            rs.getLong("row_id"),
            rs.getTimestamp("changed_at").toLocalDateTime()
        ));
    }
}
