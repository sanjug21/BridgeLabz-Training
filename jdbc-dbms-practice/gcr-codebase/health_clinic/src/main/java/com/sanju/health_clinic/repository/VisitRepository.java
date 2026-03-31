package com.sanju.health_clinic.repository;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.visit.MedicalHistoryItem;
import com.sanju.health_clinic.dto.visit.VisitCreateRequest;
import com.sanju.health_clinic.dto.visit.VisitResponse;

import javax.sql.DataSource;

@Repository
public class VisitRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertVisit;

    public VisitRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertVisit = new SimpleJdbcInsert(dataSource)
            .withTableName("visits")
            .usingGeneratedKeyColumns("id");
    }

    public long insert(VisitCreateRequest request) {
        Map<String, Object> params = Map.of(
            "appointment_id", request.appointmentId(),
            "patient_id", request.patientId(),
            "doctor_id", request.doctorId(),
            "visit_date", Date.valueOf(request.visitDate()),
            "diagnosis", request.diagnosis(),
            "notes", request.notes()
        );
        Number id = insertVisit.executeAndReturnKey(params);
        return id.longValue();
    }

    public VisitResponse findById(long visitId) {
        String sql = """
            SELECT id, appointment_id, patient_id, doctor_id, visit_date, diagnosis, notes
            FROM visits
            WHERE id = :visitId
            """;
        List<VisitResponse> results = jdbcTemplate.query(sql, Map.of("visitId", visitId), (rs, rowNum) -> new VisitResponse(
            rs.getLong("id"),
            rs.getLong("appointment_id"),
            rs.getLong("patient_id"),
            rs.getLong("doctor_id"),
            rs.getDate("visit_date").toLocalDate(),
            rs.getString("diagnosis"),
            rs.getString("notes")
        ));
        return results.isEmpty() ? null : results.get(0);
    }

    public Long findDoctorIdByVisitId(long visitId) {
        String sql = "SELECT doctor_id FROM visits WHERE id = :visitId";
        List<Long> results = jdbcTemplate.query(sql, Map.of("visitId", visitId),
            (rs, rowNum) -> rs.getLong("doctor_id"));
        return results.isEmpty() ? null : results.get(0);
    }

    public Long findPatientIdByVisitId(long visitId) {
        String sql = "SELECT patient_id FROM visits WHERE id = :visitId";
        List<Long> results = jdbcTemplate.query(sql, Map.of("visitId", visitId),
            (rs, rowNum) -> rs.getLong("patient_id"));
        return results.isEmpty() ? null : results.get(0);
    }

    public List<MedicalHistoryItem> findMedicalHistory(long patientId) {
        String sql = """
            SELECT v.id AS visit_id,
                   v.visit_date,
                   v.diagnosis,
                   a.status AS appointment_status,
                   d.full_name AS doctor_name,
                   GROUP_CONCAT(p.medicine_name SEPARATOR ', ') AS prescription_summary
            FROM visits v
            INNER JOIN appointments a ON a.id = v.appointment_id
            INNER JOIN doctors d ON d.id = v.doctor_id
            LEFT JOIN prescriptions p ON p.visit_id = v.id
            WHERE v.patient_id = :patientId
            GROUP BY v.id, v.visit_date, v.diagnosis, a.status, d.full_name
            ORDER BY v.visit_date DESC
            """;
        return jdbcTemplate.query(sql, Map.of("patientId", patientId), (rs, rowNum) -> new MedicalHistoryItem(
            rs.getLong("visit_id"),
            rs.getDate("visit_date").toLocalDate(),
            rs.getString("diagnosis"),
            rs.getString("appointment_status"),
            rs.getString("doctor_name"),
            rs.getString("prescription_summary")
        ));
    }
}
