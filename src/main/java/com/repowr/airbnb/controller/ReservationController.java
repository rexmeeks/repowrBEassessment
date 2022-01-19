package com.repowr.airbnb.controller;

import com.repowr.airbnb.dto.request.CreateRoom;
import com.repowr.airbnb.dto.request.ReserveRoom;
import com.repowr.airbnb.service.ReservationService;
import com.repowr.airbnb.validation.ReservationValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("airbnb")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationValidation reservationValidation;

    @GetMapping(path = "/getReservations", produces = "application/json")
    public ResponseEntity getReservations () {
        return reservationService.getAllReservations();
    }

    @PostMapping(path = "/reserve/{roomId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity reserveRoom(@PathVariable Integer roomId, @RequestBody ReserveRoom reserveRoom) {
        // validate that the room isn't already booked, ideally we'd want to have either a locking table or use a db
        // that allows for retrieval, locks, saves, and then unlocks
        // in the case of a service like this, where a user is filling out information like payment and stuff we'd
        // want to use a locking table that'll be like timestamp managed, where we give a user some amount of time to
        // finish their checkout process for said dates (basically acting like it's booked,) but that would take a
        // bit to implement

        // SO I will just be validating that it's not booked for the days the user sends in, if they somehow get
        // around the UI

        ResponseEntity validation = reservationValidation.validateReservationRequest(reserveRoom);
        if(validation != null) {
            return validation;
        }
        return reservationService.reserveRoom(roomId, reserveRoom);
    }

    @GetMapping(path = "/rooms/{id}", produces = "application/json")
    public ResponseEntity getRoomById (@PathVariable Integer id) {
        return reservationService.getRoomById(id);
    }

    @PostMapping(path = "rooms/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity createRoom(@RequestBody CreateRoom createRoom) {
        ResponseEntity validation = reservationValidation.validateCreateRoomRequest(createRoom);
        if(validation != null) {
            return validation;
        }
        return reservationService.createRoom(createRoom);
    }
}
