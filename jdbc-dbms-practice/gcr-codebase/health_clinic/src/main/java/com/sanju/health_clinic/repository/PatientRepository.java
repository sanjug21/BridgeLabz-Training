package com.sanju.health_clinic.repository;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.patient.PatientCreateRequest;
import com.sanju.health_clinic.dto.patient.PatientResponse;
import com.sanju.health_clinic.dto.patient.PatientUpdateRequest;
import com.sanju.health_clinic.dto.patient.PatientVisitHistoryItem;

import javax.sql.DataSource;

@Repository
public class PatientRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertPatient;

    public PatientRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertPatient = new SimpleJdbcInsert(dataSource)
            .withTableName("patients")
            .usingGeneratedKeyColumns("id");
    }

    public boolean existsByPhoneOrEmail(String phone, String email) {
        String sql = "SELECT COUNT(*) FROM patients WHERE phone = :phone OR email = :email";
        Map<String, Object> params = Map.of("phone", phone, "email", email);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    public long insert(PatientCreateRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("full_name", request.fullName());
        params.put("dob", Date.valueOf(request.dob()));
        params.put("phone", request.phone());
        params.put("email", request.email());
        params.put("address", request.address());
        params.put("blood_group", request.bloodGroup());
        Number id = insertPatient.executeAndReturnKey(params);
        return id.longValue();
    }

    public int update(long id, PatientUpdateRequest request) {
        String sql = """
            UPDATE patients
            SET full_name = COALESCE(:full_name, full_name),
                dob = COALESCE(:dob, dob),
                phone = COALESCE(:phone, phone),
                email = COALESCE(:email, email),
                address = COALESCE(:address, address),
                blood_group = COALESCE(:blood_group, blood_group)
            WHERE id = :id
            """;
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("full_name", request.fullName());
        params.put("dob", request.dob() == null ? null : Date.valueOf(request.dob()));
        params.put("phone", request.phone());
        params.put("email", request.email());
        params.put("address", request.address());
        params.put("blood_group", request.bloodGroup());
        return jdbcTemplate.update(sql, params);
    }

    public PatientResponse findById(long id) {
        String sql = """
            SELECT id, full_name, dob, phone, email, address, blood_group
            FROM patients
            WHERE id = :id
            """;
        Map<String, Object> params = Map.of("id", id);
        List<PatientResponse> results = jdbcTemplate.query(sql, params, patientRowMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    public List<PatientResponse> search(String name, Long id, String phone) {
        StringBuilder sql = new StringBuilder("""
            SELECT id, full_name, dob, phone, email, address, blood_group
            FROM patients
            WHERE 1=1
            """);
        Map<String, Object> params = new HashMap<>();
        if (name != null && !name.isBlank()) {
            sql.append(" AND full_name LIKE :name");
            params.put("name", "%" + name + "%");
        }
        if (id != null) {
            sql.append(" AND id = :id");
            params.put("id", id);
        }
        if (phone != null && !phone.isBlank()) {
            sql.append(" AND phone = :phone");
            params.put("phone", phone);
        }
        return jdbcTemplate.query(sql.toString(), params, patientRowMapper());
    }

    public List<PatientVisitHistoryItem> findVisitHistory(long patientId) {
        String sql = """
            SELECT a.id AS appointment_id,
                   a.appointment_date,
                   a.status,
                   d.full_name AS doctor_name,
                   v.visit_date,
                   v.diagnosis
            FROM appointments a
            LEFT JOIN visits v ON v.appointment_id = a.id
            LEFT JOIN doctors d ON d.id = a.doctor_id
            WHERE a.patient_id = :patientId
            ORDER BY a.appointment_date DESC, a.appointment_time DESC
            """;
        Map<String, Object> params = Map.of("patientId", patientId);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new PatientVisitHistoryItem(
            rs.getLong("appointment_id"),
            rs.getDate("appointment_date").toLocalDate(),
            rs.getString("status"),
            rs.getString("doctor_name"),
            rs.getDate("visit_date") == null ? null : rs.getDate("visit_date").toLocalDate(),
            rs.getString("diagnosis")
        ));
    }

    private RowMapper<PatientResponse> patientRowMapper() {
        return (rs, rowNum) -> new PatientResponse(
            rs.getLong("id"),
            rs.getString("full_name"),
            rs.getDate("dob").toLocalDate(),
            rs.getString("phone"),
            rs.getString("email"),
            rs.getString("address"),
            rs.getString("blood_group")
        );
    }
}
