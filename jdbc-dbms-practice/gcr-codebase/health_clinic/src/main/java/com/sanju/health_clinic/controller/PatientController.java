package com.sanju.health_clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.health_clinic.dto.patient.PatientCreateRequest;
import com.sanju.health_clinic.dto.patient.PatientResponse;
import com.sanju.health_clinic.dto.patient.PatientUpdateRequest;
import com.sanju.health_clinic.dto.patient.PatientVisitHistoryItem;
import com.sanju.health_clinic.service.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public PatientResponse register(@RequestBody PatientCreateRequest request) {
        return patientService.registerPatient(request);
    }

    @PutMapping("/{id}")
    public PatientResponse update(@PathVariable long id, @RequestBody PatientUpdateRequest request) {
        return patientService.updatePatient(id, request);
    }

    @GetMapping("/search")
    public List<PatientResponse> search(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Long id,
        @RequestParam(required = false) String phone
    ) {
        return patientService.searchPatients(name, id, phone);
    }

    @GetMapping("/{id}/visit-history")
    public List<PatientVisitHistoryItem> visitHistory(@PathVariable long id) {
        return patientService.getVisitHistory(id);
    }
}
