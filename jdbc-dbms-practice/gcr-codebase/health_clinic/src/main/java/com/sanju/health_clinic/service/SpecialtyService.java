package com.sanju.health_clinic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sanju.health_clinic.dto.specialty.SpecialtyCreateRequest;
import com.sanju.health_clinic.dto.specialty.SpecialtyResponse;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.SpecialtyRepository;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public SpecialtyResponse create(SpecialtyCreateRequest request) {
        long id = specialtyRepository.insert(request);
        return specialtyRepository.findById(id);
    }

    public List<SpecialtyResponse> list() {
        return specialtyRepository.findAll();
    }

    public SpecialtyResponse update(long id, SpecialtyCreateRequest request) {
        if (specialtyRepository.findById(id) == null) {
            throw new NotFoundException("Specialty not found.");
        }
        specialtyRepository.update(id, request);
        return specialtyRepository.findById(id);
    }

    public void delete(long id) {
        int deleted = specialtyRepository.delete(id);
        if (deleted == 0) {
            throw new NotFoundException("Specialty not found.");
        }
    }

    public SpecialtyResponse get(long id) {
        SpecialtyResponse response = specialtyRepository.findById(id);
        if (response == null) {
            throw new NotFoundException("Specialty not found.");
        }
        return response;
    }
}
