package com.sanju.health_clinic.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.sanju.health_clinic.dto.billing.BillResponse;
import com.sanju.health_clinic.dto.billing.OutstandingBillSummary;
import com.sanju.health_clinic.dto.billing.RevenueReportRow;

import javax.sql.DataSource;

@Repository
public class BillingRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertBill;

    public BillingRepository(NamedParameterJdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertBill = new SimpleJdbcInsert(dataSource)
            .withTableName("bills")
            .usingGeneratedKeyColumns("id");
    }

    public long insertBill(long visitId, BigDecimal totalAmount) {
        Map<String, Object> params = Map.of(
            "visit_id", visitId,
            "total_amount", totalAmount,
            "payment_status", "UNPAID"
        );
        Number id = insertBill.executeAndReturnKey(params);
        return id.longValue();
    }

    public int markPaid(long billId, Date paymentDate, String paymentMode) {
        String sql = """
            UPDATE bills
            SET payment_status = 'PAID', payment_date = :paymentDate, payment_mode = :paymentMode
            WHERE id = :billId
            """;
        return jdbcTemplate.update(sql, Map.of(
            "billId", billId,
            "paymentDate", paymentDate,
            "paymentMode", paymentMode
        ));
    }

    public BillResponse findById(long billId) {
        String sql = """
            SELECT id, visit_id, total_amount, payment_status, payment_date, payment_mode
            FROM bills
            WHERE id = :billId
            """;
        List<BillResponse> results = jdbcTemplate.query(sql, Map.of("billId", billId), (rs, rowNum) -> new BillResponse(
            rs.getLong("id"),
            rs.getLong("visit_id"),
            rs.getBigDecimal("total_amount"),
            rs.getString("payment_status"),
            rs.getDate("payment_date") == null ? null : rs.getDate("payment_date").toLocalDate(),
            rs.getString("payment_mode")
        ));
        return results.isEmpty() ? null : results.get(0);
    }

    public List<OutstandingBillSummary> findOutstandingBills() {
        String sql = """
            SELECT p.id AS patient_id,
                   p.full_name AS patient_name,
                   COUNT(b.id) AS unpaid_count,
                   SUM(b.total_amount) AS total_outstanding
            FROM bills b
            INNER JOIN visits v ON v.id = b.visit_id
            INNER JOIN patients p ON p.id = v.patient_id
            WHERE b.payment_status = 'UNPAID'
            GROUP BY p.id, p.full_name
            ORDER BY total_outstanding DESC
            """;
        return jdbcTemplate.query(sql, Map.of(), (rs, rowNum) -> new OutstandingBillSummary(
            rs.getLong("patient_id"),
            rs.getString("patient_name"),
            rs.getLong("unpaid_count"),
            rs.getBigDecimal("total_outstanding")
        ));
    }

    public List<RevenueReportRow> findRevenueByDate(Date fromDate, Date toDate) {
        String sql = """
            SELECT DATE(b.payment_date) AS group_key, SUM(b.total_amount) AS total_amount
            FROM bills b
            WHERE b.payment_status = 'PAID'
              AND b.payment_date BETWEEN :fromDate AND :toDate
            GROUP BY DATE(b.payment_date)
            ORDER BY DATE(b.payment_date)
            """;
        return jdbcTemplate.query(sql, Map.of("fromDate", fromDate, "toDate", toDate), (rs, rowNum) ->
            new RevenueReportRow(rs.getString("group_key"), rs.getBigDecimal("total_amount"))
        );
    }

    public List<RevenueReportRow> findRevenueByDoctor(Date fromDate, Date toDate) {
        String sql = """
            SELECT d.full_name AS group_key, SUM(b.total_amount) AS total_amount
            FROM bills b
            INNER JOIN visits v ON v.id = b.visit_id
            INNER JOIN doctors d ON d.id = v.doctor_id
            WHERE b.payment_status = 'PAID'
              AND b.payment_date BETWEEN :fromDate AND :toDate
            GROUP BY d.full_name
            ORDER BY total_amount DESC
            """;
        return jdbcTemplate.query(sql, Map.of("fromDate", fromDate, "toDate", toDate), (rs, rowNum) ->
            new RevenueReportRow(rs.getString("group_key"), rs.getBigDecimal("total_amount"))
        );
    }

    public List<RevenueReportRow> findRevenueBySpecialty(Date fromDate, Date toDate) {
        String sql = """
            SELECT s.name AS group_key, SUM(b.total_amount) AS total_amount
            FROM bills b
            INNER JOIN visits v ON v.id = b.visit_id
            INNER JOIN doctors d ON d.id = v.doctor_id
            INNER JOIN specialties s ON s.id = d.specialty_id
            WHERE b.payment_status = 'PAID'
              AND b.payment_date BETWEEN :fromDate AND :toDate
            GROUP BY s.name
            ORDER BY total_amount DESC
            """;
        return jdbcTemplate.query(sql, Map.of("fromDate", fromDate, "toDate", toDate), (rs, rowNum) ->
            new RevenueReportRow(rs.getString("group_key"), rs.getBigDecimal("total_amount"))
        );
    }
}
