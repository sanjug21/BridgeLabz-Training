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

import com.sanju.health_clinic.dto.doctor.DoctorCreateRequest;
import com.sanju.health_clinic.dto.doctor.DoctorResponse;
import com.sanju.health_clinic.dto.doctor.DoctorSpecialtyUpdateRequest;
import com.sanju.health_clinic.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public DoctorResponse addDoctor(@RequestBody DoctorCreateRequest request) {
        return doctorService.addDoctor(request);
    }

    @PutMapping("/{id}/specialty")
    public DoctorResponse updateSpecialty(@PathVariable long id, @RequestBody DoctorSpecialtyUpdateRequest request) {
        return doctorService.updateSpecialty(id, request.specialtyId());
    }

    @GetMapping("/by-specialty")
    public List<DoctorResponse> findBySpecialty(@RequestParam String name) {
        return doctorService.findBySpecialty(name);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable long id) {
        doctorService.deactivateDoctor(id);
    }
}
