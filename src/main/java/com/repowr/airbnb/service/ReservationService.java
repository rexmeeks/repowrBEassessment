package com.repowr.airbnb.service;

import com.repowr.airbnb.dto.request.Reservation;
import com.repowr.airbnb.entity.RoomEntity;
import com.repowr.airbnb.repository.BookingRepository;
import com.repowr.airbnb.repository.RoomRepository;
import com.repowr.airbnb.transformer.ReservationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReservationTransformer reservationTransformer;

    public ResponseEntity getRoomById(Integer id) {
        Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(id);
        // this is debatable, it'd either be a 200 with a blank body, or a 404, because this specific resource
        // doesn't exist, I went with 404
        return optionalRoomEntity.<ResponseEntity<Object>>map(roomEntity -> new ResponseEntity<>(reservationTransformer.roomEntityToRoomDto(roomEntity), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity reserveRoom(Reservation reservation) {
        return new ResponseEntity(HttpStatus.OK);
    }
    //todo reserveRoomByDateAndId

    //todo getAllReservations - this needs to return the dates that an airBnB is booked for
}
