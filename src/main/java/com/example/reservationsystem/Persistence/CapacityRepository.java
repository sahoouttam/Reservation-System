package com.example.reservationsystem.Persistence;

import com.example.reservationsystem.Business.Capacity;
import com.example.reservationsystem.Business.ReservationType;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacityRepository extends JpaRepository<Capacity, Long> {
    
    Capacity findByReservationType(ReservationType reservationType);
}
