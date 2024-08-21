package com.example.queue.controller;

import com.example.queue.dto.ReservationRequestDTO;
import com.example.queue.model.Reservation;
import com.example.queue.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.createReservation(reservationRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getCurrentWeekReservations() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.getCurrentWeekReservations());
    }
}
