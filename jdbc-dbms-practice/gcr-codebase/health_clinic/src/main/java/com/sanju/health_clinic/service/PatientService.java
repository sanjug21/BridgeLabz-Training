package com.sanju.health_clinic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sanju.health_clinic.dto.patient.PatientCreateRequest;
import com.sanju.health_clinic.dto.patient.PatientResponse;
import com.sanju.health_clinic.dto.patient.PatientUpdateRequest;
import com.sanju.health_clinic.dto.patient.PatientVisitHistoryItem;
import com.sanju.health_clinic.exception.ConflictException;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponse registerPatient(PatientCreateRequest request) {
        if (patientRepository.existsByPhoneOrEmail(request.phone(), request.email())) {
            throw new ConflictException("Patient with the same phone or email already exists.");
        }
        long id = patientRepository.insert(request);
        return patientRepository.findById(id);
    }

    public PatientResponse updatePatient(long id, PatientUpdateRequest request) {
        PatientResponse existing = patientRepository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Patient not found.");
        }
        patientRepository.update(id, request);
        return patientRepository.findById(id);
    }

    public List<PatientResponse> searchPatients(String name, Long id, String phone) {
        return patientRepository.search(name, id, phone);
    }

    public List<PatientVisitHistoryItem> getVisitHistory(long patientId) {
        return patientRepository.findVisitHistory(patientId);
    }
}
