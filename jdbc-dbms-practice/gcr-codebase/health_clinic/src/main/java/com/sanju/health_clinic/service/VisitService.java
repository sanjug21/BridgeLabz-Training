package com.sanju.health_clinic.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanju.health_clinic.dto.visit.MedicalHistoryItem;
import com.sanju.health_clinic.dto.visit.PrescriptionRequest;
import com.sanju.health_clinic.dto.visit.VisitCreateRequest;
import com.sanju.health_clinic.dto.visit.VisitResponse;
import com.sanju.health_clinic.exception.BadRequestException;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.AppointmentRepository;
import com.sanju.health_clinic.repository.PrescriptionRepository;
import com.sanju.health_clinic.repository.VisitRepository;

@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionRepository prescriptionRepository;

    public VisitService(
        VisitRepository visitRepository,
        AppointmentRepository appointmentRepository,
        PrescriptionRepository prescriptionRepository
    ) {
        this.visitRepository = visitRepository;
        this.appointmentRepository = appointmentRepository;
        this.prescriptionRepository = prescriptionRepository;
    }

    @Transactional
    public VisitResponse recordVisit(VisitCreateRequest request) {
        if (appointmentRepository.findById(request.appointmentId()) == null) {
            throw new BadRequestException("Appointment not found.");
        }
        long id = visitRepository.insert(request);
        appointmentRepository.updateStatus(request.appointmentId(), "COMPLETED");
        return visitRepository.findById(id);
    }

    public List<MedicalHistoryItem> getMedicalHistory(long patientId) {
        return visitRepository.findMedicalHistory(patientId);
    }

    public void addPrescriptions(long visitId, List<PrescriptionRequest> prescriptions) {
        if (visitRepository.findById(visitId) == null) {
            throw new NotFoundException("Visit not found.");
        }
        prescriptionRepository.insertBatch(visitId, prescriptions);
    }
}
