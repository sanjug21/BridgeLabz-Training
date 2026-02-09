package com.sanju.health_clinic.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanju.health_clinic.dto.specialty.SpecialtyCreateRequest;
import com.sanju.health_clinic.dto.specialty.SpecialtyResponse;
import com.sanju.health_clinic.service.SpecialtyService;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @PostMapping
    public SpecialtyResponse create(@RequestBody SpecialtyCreateRequest request) {
        return specialtyService.create(request);
    }

    @GetMapping
    public List<SpecialtyResponse> list() {
        return specialtyService.list();
    }

    @GetMapping("/{id}")
    public SpecialtyResponse get(@PathVariable long id) {
        return specialtyService.get(id);
    }

    @PutMapping("/{id}")
    public SpecialtyResponse update(@PathVariable long id, @RequestBody SpecialtyCreateRequest request) {
        return specialtyService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        specialtyService.delete(id);
    }
}
