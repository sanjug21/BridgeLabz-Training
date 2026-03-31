package com.sanju.health_clinic.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanju.health_clinic.dto.doctor.DoctorCreateRequest;
import com.sanju.health_clinic.dto.doctor.DoctorResponse;
import com.sanju.health_clinic.exception.BadRequestException;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.DoctorRepository;
import com.sanju.health_clinic.repository.SpecialtyRepository;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;

    public DoctorService(DoctorRepository doctorRepository, SpecialtyRepository specialtyRepository) {
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
    }

    public DoctorResponse addDoctor(DoctorCreateRequest request) {
        if (specialtyRepository.findById(request.specialtyId()) == null) {
            throw new BadRequestException("Specialty not found.");
        }
        long id = doctorRepository.insert(request);
        return doctorRepository.findById(id);
    }

    public DoctorResponse updateSpecialty(long doctorId, long specialtyId) {
        if (doctorRepository.findById(doctorId) == null) {
            throw new NotFoundException("Doctor not found.");
        }
        if (specialtyRepository.findById(specialtyId) == null) {
            throw new BadRequestException("Specialty not found.");
        }
        doctorRepository.updateSpecialty(doctorId, specialtyId);
        return doctorRepository.findById(doctorId);
    }

    public List<DoctorResponse> findBySpecialty(String specialtyName) {
        return doctorRepository.findBySpecialtyName(specialtyName);
    }

    public void deactivateDoctor(long doctorId) {
        if (doctorRepository.findById(doctorId) == null) {
            throw new NotFoundException("Doctor not found.");
        }
        boolean hasFuture = doctorRepository.hasFutureAppointments(doctorId, Date.valueOf(LocalDate.now()));
        if (hasFuture) {
            throw new BadRequestException("Doctor has future appointments and cannot be deactivated.");
        }
        doctorRepository.deactivate(doctorId);
    }
}
