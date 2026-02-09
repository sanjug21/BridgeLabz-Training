package com.sanju.health_clinic.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PaymentRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertPayment(long billId, BigDecimal amount, Date paymentDate, String paymentMode, String referenceNo) {
        String sql = """
            INSERT INTO payment_transactions (bill_id, amount, payment_date, payment_mode, reference_no)
            VALUES (:billId, :amount, :paymentDate, :paymentMode, :referenceNo)
            """;
        jdbcTemplate.update(sql, Map.of(
            "billId", billId,
            "amount", amount,
            "paymentDate", paymentDate,
            "paymentMode", paymentMode,
            "referenceNo", referenceNo
        ));
    }
}
