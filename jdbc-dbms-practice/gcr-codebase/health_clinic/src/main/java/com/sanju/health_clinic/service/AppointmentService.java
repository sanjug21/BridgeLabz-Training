package com.sanju.health_clinic.service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanju.health_clinic.dto.appointment.AppointmentAvailabilityItem;
import com.sanju.health_clinic.dto.appointment.AppointmentCancelRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentCreateRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentRescheduleRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentResponse;
import com.sanju.health_clinic.dto.appointment.DailyScheduleItem;
import com.sanju.health_clinic.exception.BadRequestException;
import com.sanju.health_clinic.exception.NotFoundException;
import com.sanju.health_clinic.repository.AppointmentRepository;
import com.sanju.health_clinic.repository.DoctorRepository;
import com.sanju.health_clinic.repository.PatientRepository;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(
        AppointmentRepository appointmentRepository,
        PatientRepository patientRepository,
        DoctorRepository doctorRepository
    ) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public AppointmentResponse bookAppointment(AppointmentCreateRequest request) {
        if (patientRepository.findById(request.patientId()) == null) {
            throw new BadRequestException("Patient not found.");
        }
        if (doctorRepository.findById(request.doctorId()) == null) {
            throw new BadRequestException("Doctor not found.");
        }
        boolean available = appointmentRepository.isSlotAvailable(
            request.doctorId(),
            Date.valueOf(request.appointmentDate()),
            Time.valueOf(request.appointmentTime())
        );
        if (!available) {
            throw new BadRequestException("Selected slot is not available.");
        }
        long id = appointmentRepository.insert(request);
        return appointmentRepository.findById(id);
    }

    public List<AppointmentAvailabilityItem> checkAvailability(long doctorId, LocalDate date) {
        return appointmentRepository.getAvailabilityByDate(doctorId, Date.valueOf(date));
    }

    @Transactional
    public void cancelAppointment(long appointmentId, AppointmentCancelRequest request) {
        AppointmentResponse existing = appointmentRepository.findById(appointmentId);
        if (existing == null) {
            throw new NotFoundException("Appointment not found.");
        }
        appointmentRepository.updateStatus(appointmentId, "CANCELLED");
        appointmentRepository.insertAudit(appointmentId, "CANCELLED", request.reason());
    }

    @Transactional
    public AppointmentResponse reschedule(long appointmentId, AppointmentRescheduleRequest request) {
        AppointmentResponse existing = appointmentRepository.findById(appointmentId);
        if (existing == null) {
            throw new NotFoundException("Appointment not found.");
        }
        boolean available = appointmentRepository.isSlotAvailable(
            request.doctorId(),
            Date.valueOf(request.appointmentDate()),
            Time.valueOf(request.appointmentTime())
        );
        if (!available) {
            throw new BadRequestException("Selected slot is not available.");
        }
        appointmentRepository.updateSchedule(
            appointmentId,
            request.doctorId(),
            Date.valueOf(request.appointmentDate()),
            Time.valueOf(request.appointmentTime())
        );
        return appointmentRepository.findById(appointmentId);
    }

    public List<DailyScheduleItem> dailySchedule(LocalDate date) {
        return appointmentRepository.findDailySchedule(Date.valueOf(date));
    }
}
