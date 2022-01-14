package com.repowr.airbnb.controller;

import com.repowr.airbnb.dto.request.Reservation;
import com.repowr.airbnb.dto.response.Reservations;
import com.repowr.airbnb.dto.response.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("airbnb")
public class ReservationController {

    @GetMapping(path = "/getReservations", produces = "application/json")
    public ResponseEntity<Reservations> getReservations () {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/reserve", consumes = "application/json", produces = "application/json")
    public ResponseEntity reserveRoom(@RequestBody RequestEntity<Reservation> reservation) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path = "/rooms/{id}", produces = "application/json")
    public ResponseEntity<Room> getRoomById (@PathVariable Integer id) {
        return new ResponseEntity<Room>(HttpStatus.OK);
    }
}
