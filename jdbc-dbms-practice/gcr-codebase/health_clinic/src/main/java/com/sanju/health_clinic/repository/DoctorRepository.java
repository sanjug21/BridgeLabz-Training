package com.sanju.health_clinic.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.doctor.DoctorCreateRequest;
import com.sanju.health_clinic.dto.doctor.DoctorResponse;

import javax.sql.DataSource;

@Repository
public class DoctorRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertDoctor;

    public DoctorRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertDoctor = new SimpleJdbcInsert(dataSource)
            .withTableName("doctors")
            .usingGeneratedKeyColumns("id");
    }

    public long insert(DoctorCreateRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("full_name", request.fullName());
        params.put("phone", request.phone());
        params.put("email", request.email());
        params.put("consultation_fee", request.consultationFee());
        params.put("specialty_id", request.specialtyId());
        Number id = insertDoctor.executeAndReturnKey(params);
        return id.longValue();
    }

    public int updateSpecialty(long doctorId, long specialtyId) {
        String sql = "UPDATE doctors SET specialty_id = :specialtyId WHERE id = :doctorId";
        Map<String, Object> params = Map.of("specialtyId", specialtyId, "doctorId", doctorId);
        return jdbcTemplate.update(sql, params);
    }

    public int deactivate(long doctorId) {
        String sql = "UPDATE doctors SET is_active = FALSE WHERE id = :doctorId";
        Map<String, Object> params = Map.of("doctorId", doctorId);
        return jdbcTemplate.update(sql, params);
    }

    public boolean hasFutureAppointments(long doctorId, Date fromDate) {
        String sql = """
            SELECT COUNT(*)
            FROM appointments
            WHERE doctor_id = :doctorId
              AND appointment_date >= :fromDate
              AND status = 'SCHEDULED'
            """;
        Map<String, Object> params = Map.of("doctorId", doctorId, "fromDate", fromDate);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

    public List<DoctorResponse> findBySpecialtyName(String specialtyName) {
        String sql = """
            SELECT d.id, d.full_name, d.phone, d.email, d.consultation_fee, d.specialty_id,
                   s.name AS specialty_name, d.is_active
            FROM doctors d
            INNER JOIN specialties s ON s.id = d.specialty_id
            WHERE s.name = :specialtyName
            """;
        Map<String, Object> params = Map.of("specialtyName", specialtyName);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new DoctorResponse(
            rs.getLong("id"),
            rs.getString("full_name"),
            rs.getString("phone"),
            rs.getString("email"),
            rs.getBigDecimal("consultation_fee"),
            rs.getLong("specialty_id"),
            rs.getString("specialty_name"),
            rs.getBoolean("is_active")
        ));
    }

    public DoctorResponse findById(long doctorId) {
        String sql = """
            SELECT d.id, d.full_name, d.phone, d.email, d.consultation_fee, d.specialty_id,
                   s.name AS specialty_name, d.is_active
            FROM doctors d
            INNER JOIN specialties s ON s.id = d.specialty_id
            WHERE d.id = :doctorId
            """;
        Map<String, Object> params = Map.of("doctorId", doctorId);
        List<DoctorResponse> results = jdbcTemplate.query(sql, params, (rs, rowNum) -> new DoctorResponse(
            rs.getLong("id"),
            rs.getString("full_name"),
            rs.getString("phone"),
            rs.getString("email"),
            rs.getBigDecimal("consultation_fee"),
            rs.getLong("specialty_id"),
            rs.getString("specialty_name"),
            rs.getBoolean("is_active")
        ));
        return results.isEmpty() ? null : results.get(0);
    }

    public BigDecimal findConsultationFeeByDoctorId(long doctorId) {
        String sql = "SELECT consultation_fee FROM doctors WHERE id = :doctorId";
        Map<String, Object> params = Map.of("doctorId", doctorId);
        List<BigDecimal> results = jdbcTemplate.query(sql, params, (rs, rowNum) -> rs.getBigDecimal("consultation_fee"));
        return results.isEmpty() ? null : results.get(0);
    }
}
