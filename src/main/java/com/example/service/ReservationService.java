package com.example.service;

import com.example.data.Reservation;
import com.example.data.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    
    private final ReservationRepository reservationRepository;
    
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    
    // Méthodes pour gérer la disponibilité, créer réservations, etc.
}