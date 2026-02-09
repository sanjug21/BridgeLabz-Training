package com.sanju.health_clinic.service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.sanju.health_clinic.dto.admin.AuditLogResponse;
import com.sanju.health_clinic.dto.admin.BackupResponse;
import com.sanju.health_clinic.repository.AuditRepository;

@Service
public class AdminService {

    private final DataSource dataSource;
    private final AuditRepository auditRepository;

    public AdminService(DataSource dataSource, AuditRepository auditRepository) {
        this.dataSource = dataSource;
        this.auditRepository = auditRepository;
    }

    public BackupResponse backupDatabase() throws Exception {
        List<String> tables = List.of(
            "patients",
            "specialties",
            "doctors",
            "appointments",
            "appointment_audit",
            "visits",
            "prescriptions",
            "bills",
            "payment_transactions",
            "audit_log"
        );

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = Path.of("backups", timestamp);
        Files.createDirectories(backupDir);

        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet rs = metaData.getTables(connection.getCatalog(), null, "%", new String[] { "TABLE" })) {
                List<String> existingTables = new ArrayList<>();
                while (rs.next()) {
                    existingTables.add(rs.getString("TABLE_NAME"));
                }
                for (String table : tables) {
                    if (!existingTables.contains(table)) {
                        continue;
                    }
                    exportTable(connection, table, backupDir);
                }
            }
        }

        return new BackupResponse(backupDir.toString(), tables);
    }

    public List<AuditLogResponse> auditLogs(String tableName) {
        return auditRepository.findAuditLogs(tableName);
    }

    private void exportTable(Connection connection, String tableName, Path backupDir) throws Exception {
        String sql = "SELECT * FROM " + tableName;
        try (Statement statement = connection.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();
            Path file = backupDir.resolve(tableName + ".csv");
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                builder.append(meta.getColumnLabel(i));
                if (i < columnCount) {
                    builder.append(',');
                }
            }
            builder.append('\n');
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String value = rs.getString(i);
                    builder.append(value == null ? "" : value.replace(",", " "));
                    if (i < columnCount) {
                        builder.append(',');
                    }
                }
                builder.append('\n');
            }
            Files.writeString(file, builder.toString(), StandardCharsets.UTF_8);
        }
    }
}
