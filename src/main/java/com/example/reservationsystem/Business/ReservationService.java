package com.example.reservationsystem.Business;

import java.util.List;

import com.example.reservationsystem.Persistence.CapacityRepository;
import com.example.reservationsystem.Persistence.ReservationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ReservationService {
    
    private final ReservationRepository reservationRepository;
    private final CapacityRepository capacityRepository;

    public ReservationService(ReservationRepository reservationRepository, CapacityRepository capacityRepository) {
        this.reservationRepository = reservationRepository;
        this.capacityRepository = capacityRepository;
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation get(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(Reservation reservation) {
        int capacity = capacityRepository.findByReservationType(reservation.getReservationType()).getCapacity();
        int overlappingReservations = reservationRepository.findReservationsByReservationDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
            reservation.getReservationDate(), 
            reservation.getStartTime(), reservation.getEndTime(),
            reservation.getStartTime(), reservation.getEndTime()).size();
        
            if (overlappingReservations >= capacity) {
            throw new CapacityFullException("The capacity is full is desired time");
        }

        return reservationRepository.save(reservation).getId();
    }

    public void update(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservationRepository.save(reservation);
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}
