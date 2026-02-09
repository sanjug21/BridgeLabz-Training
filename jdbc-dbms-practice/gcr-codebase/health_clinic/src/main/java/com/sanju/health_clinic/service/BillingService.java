package com.sanju.health_clinic.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanju.health_clinic.dto.billing.BillCreateRequest;
import com.sanju.health_clinic.dto.billing.BillResponse;
import com.sanju.health_clinic.dto.billing.OutstandingBillSummary;
import com.sanju.health_clinic.dto.billing.PaymentRequest;
import com.sanju.health_clinic.dto.billing.RevenueReportRow;
import com.sanju.health_clinic.exception.BadRequestException;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.BillingRepository;
import com.sanju.health_clinic.repository.DoctorRepository;
import com.sanju.health_clinic.repository.PaymentRepository;
import com.sanju.health_clinic.repository.VisitRepository;

@Service
public class BillingService {

    private final BillingRepository billingRepository;
    private final PaymentRepository paymentRepository;
    private final VisitRepository visitRepository;
    private final DoctorRepository doctorRepository;

    public BillingService(
        BillingRepository billingRepository,
        PaymentRepository paymentRepository,
        VisitRepository visitRepository,
        DoctorRepository doctorRepository
    ) {
        this.billingRepository = billingRepository;
        this.paymentRepository = paymentRepository;
        this.visitRepository = visitRepository;
        this.doctorRepository = doctorRepository;
    }

    public BillResponse generateBill(BillCreateRequest request) {
        Long doctorId = visitRepository.findDoctorIdByVisitId(request.visitId());
        if (doctorId == null) {
            throw new BadRequestException("Visit not found.");
        }
        BigDecimal consultationFee = doctorRepository.findConsultationFeeByDoctorId(doctorId);
        if (consultationFee == null) {
            throw new BadRequestException("Doctor not found for visit.");
        }
        BigDecimal additional = request.additionalCharges() == null ? BigDecimal.ZERO : request.additionalCharges();
        BigDecimal total = consultationFee.add(additional);
        long id = billingRepository.insertBill(request.visitId(), total);
        return billingRepository.findById(id);
    }

    @Transactional
    public BillResponse recordPayment(long billId, PaymentRequest request) {
        BillResponse bill = billingRepository.findById(billId);
        if (bill == null) {
            throw new NotFoundException("Bill not found.");
        }
        if (request.amount() == null || request.amount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Payment amount must be positive.");
        }
        billingRepository.markPaid(billId, Date.valueOf(request.paymentDate()), request.paymentMode());
        paymentRepository.insertPayment(
            billId,
            request.amount(),
            Date.valueOf(request.paymentDate()),
            request.paymentMode(),
            request.referenceNo()
        );
        return billingRepository.findById(billId);
    }

    public List<OutstandingBillSummary> outstandingBills() {
        return billingRepository.findOutstandingBills();
    }

    public List<RevenueReportRow> revenueReport(LocalDate from, LocalDate to, String groupBy) {
        if (from == null || to == null) {
            throw new BadRequestException("from and to dates are required.");
        }
        Date fromDate = Date.valueOf(from);
        Date toDate = Date.valueOf(to);
        return switch (groupBy == null ? "date" : groupBy) {
            case "doctor" -> billingRepository.findRevenueByDoctor(fromDate, toDate);
            case "specialty" -> billingRepository.findRevenueBySpecialty(fromDate, toDate);
            default -> billingRepository.findRevenueByDate(fromDate, toDate);
        };
    }
}
