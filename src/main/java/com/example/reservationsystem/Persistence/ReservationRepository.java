package com.example.reservationsystem.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.reservationsystem.Business.Reservation;
import com.example.reservationsystem.Business.ReservationType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    List<Reservation> findReservationsByReservationType(ReservationType reservationType);

    List<Reservation> findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(LocalDate reservationDate, LocalTime startTime, LocalTime endTime, LocalTime betweenStart, LocalTime betweenEnd);
}
