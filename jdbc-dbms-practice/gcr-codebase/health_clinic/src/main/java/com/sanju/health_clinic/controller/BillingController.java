package com.sanju.health_clinic.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.health_clinic.dto.billing.BillCreateRequest;
import com.sanju.health_clinic.dto.billing.BillResponse;
import com.sanju.health_clinic.dto.billing.OutstandingBillSummary;
import com.sanju.health_clinic.dto.billing.PaymentRequest;
import com.sanju.health_clinic.dto.billing.RevenueReportRow;
import com.sanju.health_clinic.service.BillingService;

@RestController
@RequestMapping("/api/bills")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @PostMapping
    public BillResponse generate(@RequestBody BillCreateRequest request) {
        return billingService.generateBill(request);
    }

    @PutMapping("/{billId}/payment")
    public BillResponse recordPayment(@PathVariable long billId, @RequestBody PaymentRequest request) {
        return billingService.recordPayment(billId, request);
    }

    @GetMapping("/outstanding")
    public List<OutstandingBillSummary> outstandingBills() {
        return billingService.outstandingBills();
    }

    @GetMapping("/revenue")
    public List<RevenueReportRow> revenueReport(
        @RequestParam LocalDate from,
        @RequestParam LocalDate to,
        @RequestParam(required = false) String groupBy
    ) {
        return billingService.revenueReport(from, to, groupBy);
    }
}
