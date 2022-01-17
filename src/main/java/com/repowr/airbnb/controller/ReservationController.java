package com.repowr.airbnb.controller;

import com.repowr.airbnb.dto.request.Reservation;
import com.repowr.airbnb.dto.response.Reservations;
import com.repowr.airbnb.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("airbnb")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping(path = "/getReservations", produces = "application/json")
    public ResponseEntity<Reservations> getReservations () {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/reserve", consumes = "application/json", produces = "application/json")
    public ResponseEntity reserveRoom(@RequestBody Reservation reservation) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path = "/rooms/{id}", produces = "application/json")
    public ResponseEntity getRoomById (@PathVariable Integer id) {
        return reservationService.getRoomById(id);
    }
}
