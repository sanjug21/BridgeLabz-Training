package com.sanju.health_clinic.repository;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.appointment.AppointmentAvailabilityItem;
import com.sanju.health_clinic.dto.appointment.AppointmentCreateRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentResponse;
import com.sanju.health_clinic.dto.appointment.DailyScheduleItem;

import javax.sql.DataSource;

@Repository
public class AppointmentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertAppointment;

    public AppointmentRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertAppointment = new SimpleJdbcInsert(dataSource)
            .withTableName("appointments")
            .usingGeneratedKeyColumns("id");
    }

    public long insert(AppointmentCreateRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("patient_id", request.patientId());
        params.put("doctor_id", request.doctorId());
        params.put("appointment_date", Date.valueOf(request.appointmentDate()));
        params.put("appointment_time", Time.valueOf(request.appointmentTime()));
        params.put("status", "SCHEDULED");
        params.put("notes", request.notes());
        Number id = insertAppointment.executeAndReturnKey(params);
        return id.longValue();
    }

    public boolean isSlotAvailable(long doctorId, Date date, Time time) {
        String sql = """
            SELECT COUNT(*)
            FROM appointments
            WHERE doctor_id = :doctorId
              AND appointment_date = :date
              AND appointment_time = :time
              AND status = 'SCHEDULED'
            """;
        Map<String, Object> params = Map.of("doctorId", doctorId, "date", date, "time", time);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count == null || count == 0;
    }

    public List<AppointmentAvailabilityItem> getAvailabilityByDate(long doctorId, Date date) {
        String sql = """
            SELECT appointment_time, COUNT(*) AS booked_count
            FROM appointments
            WHERE doctor_id = :doctorId
              AND appointment_date = :date
            GROUP BY appointment_time
            ORDER BY appointment_time
            """;
        Map<String, Object> params = Map.of("doctorId", doctorId, "date", date);
        return jdbcTemplate.query(sql, params, (rs, rowNum) -> new AppointmentAvailabilityItem(
            rs.getTime("appointment_time").toLocalTime(),
            rs.getInt("booked_count")
        ));
    }

    public int updateStatus(long appointmentId, String status) {
        String sql = "UPDATE appointments SET status = :status WHERE id = :appointmentId";
        return jdbcTemplate.update(sql, Map.of("status", status, "appointmentId", appointmentId));
    }

    public int updateSchedule(long appointmentId, long doctorId, Date date, Time time) {
        String sql = """
            UPDATE appointments
            SET doctor_id = :doctorId,
                appointment_date = :date,
                appointment_time = :time
            WHERE id = :appointmentId
            """;
        Map<String, Object> params = Map.of(
            "doctorId", doctorId,
            "date", date,
            "time", time,
            "appointmentId", appointmentId
        );
        return jdbcTemplate.update(sql, params);
    }

    public AppointmentResponse findById(long appointmentId) {
        String sql = """
            SELECT id, patient_id, doctor_id, appointment_date, appointment_time, status, notes
            FROM appointments
            WHERE id = :appointmentId
            """;
        List<AppointmentResponse> results = jdbcTemplate.query(sql, Map.of("appointmentId", appointmentId),
            (rs, rowNum) -> new AppointmentResponse(
                rs.getLong("id"),
                rs.getLong("patient_id"),
                rs.getLong("doctor_id"),
                rs.getDate("appointment_date").toLocalDate(),
                rs.getTime("appointment_time").toLocalTime(),
                rs.getString("status"),
                rs.getString("notes")
            )
        );
        return results.isEmpty() ? null : results.get(0);
    }

    public void insertAudit(long appointmentId, String action, String reason) {
        String sql = """
            INSERT INTO appointment_audit (appointment_id, action, reason)
            VALUES (:appointmentId, :action, :reason)
            """;
        jdbcTemplate.update(sql, Map.of(
            "appointmentId", appointmentId,
            "action", action,
            "reason", reason
        ));
    }

    public List<DailyScheduleItem> findDailySchedule(Date date) {
        String sql = """
            SELECT a.id AS appointment_id,
                   a.appointment_date,
                   a.appointment_time,
                   p.full_name AS patient_name,
                   d.full_name AS doctor_name,
                   a.status
            FROM appointments a
            INNER JOIN patients p ON p.id = a.patient_id
            INNER JOIN doctors d ON d.id = a.doctor_id
            WHERE a.appointment_date = :date
            ORDER BY a.appointment_time
            """;
        return jdbcTemplate.query(sql, Map.of("date", date), (rs, rowNum) -> new DailyScheduleItem(
            rs.getLong("appointment_id"),
            rs.getDate("appointment_date").toLocalDate(),
            rs.getTime("appointment_time").toLocalTime(),
            rs.getString("patient_name"),
            rs.getString("doctor_name"),
            rs.getString("status")
        ));
    }
}
