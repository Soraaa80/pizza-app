package com.example.controller;

import com.example.data.Reservation;
import com.example.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    
    private final ReservationService reservationService;
    
    // Injection + méthodes pour afficher formulaire, traiter réservation, etc.
}