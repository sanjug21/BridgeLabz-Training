package com.sanju.health_clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.health_clinic.dto.visit.MedicalHistoryItem;
import com.sanju.health_clinic.dto.visit.PrescriptionRequest;
import com.sanju.health_clinic.dto.visit.VisitCreateRequest;
import com.sanju.health_clinic.dto.visit.VisitResponse;
import com.sanju.health_clinic.service.VisitService;

@RestController
@RequestMapping("/api/visits")
public class VisitController {

    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public VisitResponse record(@RequestBody VisitCreateRequest request) {
        return visitService.recordVisit(request);
    }

    @PostMapping("/{visitId}/prescriptions")
    public void addPrescriptions(@PathVariable long visitId, @RequestBody List<PrescriptionRequest> prescriptions) {
        visitService.addPrescriptions(visitId, prescriptions);
    }

    @GetMapping("/patient/{patientId}")
    public List<MedicalHistoryItem> medicalHistory(@PathVariable long patientId) {
        return visitService.getMedicalHistory(patientId);
    }
}
