package com.sanju.health_clinic.dto.appointment;

import java.time.LocalTime;

public record AppointmentAvailabilityItem(LocalTime slotTime, int bookedCount) {
}
