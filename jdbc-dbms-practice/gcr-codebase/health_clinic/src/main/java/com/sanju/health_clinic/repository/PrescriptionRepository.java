package com.sanju.health_clinic.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.visit.PrescriptionRequest;

@Repository
public class PrescriptionRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PrescriptionRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("unchecked")
    public void insertBatch(long visitId, List<PrescriptionRequest> prescriptions) {
        String sql = """
            INSERT INTO prescriptions (visit_id, medicine_name, dosage, duration_days, instructions)
            VALUES (:visitId, :medicineName, :dosage, :durationDays, :instructions)
            """;
        List<java.util.Map<String, Object>> batchValues = prescriptions.stream().map(item ->
            java.util.Map.<String, Object>of(
                "visitId", visitId,
                "medicineName", item.medicineName(),
                "dosage", item.dosage(),
                "durationDays", item.durationDays(),
                "instructions", item.instructions()
            )
        ).toList();
        jdbcTemplate.batchUpdate(sql, batchValues.toArray(new java.util.Map[0]));
    }
}
