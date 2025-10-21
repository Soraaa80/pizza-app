package com.example.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;
    
    @Column(nullable = false)
    private String customerEmail;
    
    @Column(nullable = false)
    private int numberOfPeople;
    
    @Column(nullable = false)
    private LocalDateTime eventDateTime; // Date/heure de l'événement
    
    @Column(nullable = false)
    private LocalDateTime reservationDate; // Date de la réservation
    
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    
    // === ENUM ===
    public enum ReservationStatus {
        PENDING, CONFIRMED, CANCELLED
    }

    // === CONSTRUCTEURS ===
    public Reservation() {}
    
    public Reservation(String customerName, String customerEmail, int numberOfPeople, 
                      LocalDateTime eventDateTime) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.numberOfPeople = numberOfPeople;
        this.eventDateTime = eventDateTime;
        this.reservationDate = LocalDateTime.now();
        this.status = ReservationStatus.PENDING;
    }

    // === GETTERS/SETTERS ===
    // (je te les épargne mais tu les ajoutes)
}