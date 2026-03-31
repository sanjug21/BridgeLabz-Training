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

import com.sanju.health_clinic.dto.appointment.AppointmentAvailabilityItem;
import com.sanju.health_clinic.dto.appointment.AppointmentCancelRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentCreateRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentRescheduleRequest;
import com.sanju.health_clinic.dto.appointment.AppointmentResponse;
import com.sanju.health_clinic.dto.appointment.DailyScheduleItem;
import com.sanju.health_clinic.service.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentResponse book(@RequestBody AppointmentCreateRequest request) {
        return appointmentService.bookAppointment(request);
    }

    @GetMapping("/availability")
    public List<AppointmentAvailabilityItem> availability(
        @RequestParam long doctorId,
        @RequestParam LocalDate date
    ) {
        return appointmentService.checkAvailability(doctorId, date);
    }

    @PutMapping("/{id}/cancel")
    public void cancel(@PathVariable long id, @RequestBody AppointmentCancelRequest request) {
        appointmentService.cancelAppointment(id, request);
    }

    @PutMapping("/{id}/reschedule")
    public AppointmentResponse reschedule(@PathVariable long id, @RequestBody AppointmentRescheduleRequest request) {
        return appointmentService.reschedule(id, request);
    }

    @GetMapping("/daily")
    public List<DailyScheduleItem> dailySchedule(@RequestParam LocalDate date) {
        return appointmentService.dailySchedule(date);
    }
}
