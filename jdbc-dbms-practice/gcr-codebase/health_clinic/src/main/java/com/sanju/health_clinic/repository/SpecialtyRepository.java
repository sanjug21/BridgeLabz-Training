package com.sanju.health_clinic.repository;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.specialty.SpecialtyCreateRequest;
import com.sanju.health_clinic.dto.specialty.SpecialtyResponse;

import javax.sql.DataSource;

@Repository
public class SpecialtyRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertSpecialty;

    public SpecialtyRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertSpecialty = new SimpleJdbcInsert(dataSource)
            .withTableName("specialties")
            .usingGeneratedKeyColumns("id");
    }

    public long insert(SpecialtyCreateRequest request) {
        Map<String, Object> params = Map.of(
            "name", request.name(),
            "is_active", request.active() == null ? Boolean.TRUE : request.active()
        );
        Number id = insertSpecialty.executeAndReturnKey(params);
        return id.longValue();
    }

    public List<SpecialtyResponse> findAll() {
        String sql = "SELECT id, name, is_active FROM specialties ORDER BY name";
        return jdbcTemplate.query(sql, Map.of(), (rs, rowNum) -> new SpecialtyResponse(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getBoolean("is_active")
        ));
    }

    public int update(long id, SpecialtyCreateRequest request) {
        String sql = """
            UPDATE specialties
            SET name = COALESCE(:name, name),
                is_active = COALESCE(:is_active, is_active)
            WHERE id = :id
            """;
        Map<String, Object> params = Map.of(
            "id", id,
            "name", request.name(),
            "is_active", request.active()
        );
        return jdbcTemplate.update(sql, params);
    }

    public int delete(long id) {
        String sql = "DELETE FROM specialties WHERE id = :id";
        return jdbcTemplate.update(sql, Map.of("id", id));
    }

    public SpecialtyResponse findById(long id) {
        String sql = "SELECT id, name, is_active FROM specialties WHERE id = :id";
        List<SpecialtyResponse> results = jdbcTemplate.query(sql, Map.of("id", id), (rs, rowNum) -> new SpecialtyResponse(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getBoolean("is_active")
        ));
        return results.isEmpty() ? null : results.get(0);
    }
}
