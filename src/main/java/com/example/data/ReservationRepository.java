package com.example.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    
    // Trouver les réservations pour un créneau spécifique
    List<Reservation> findByEventDateTime(LocalDateTime eventDateTime);
    
    // Trouver les créneaux disponibles (où le nombre de personnes < limite)
    @Query("SELECT r.eventDateTime FROM Reservation r GROUP BY r.eventDateTime HAVING SUM(r.numberOfPeople) < :maxCapacity")
    List<LocalDateTime> findAvailableSlots(@Param("maxCapacity") int maxCapacity);
    
    // Réservations par statut
    List<Reservation> findByStatus(Reservation.ReservationStatus status);
}