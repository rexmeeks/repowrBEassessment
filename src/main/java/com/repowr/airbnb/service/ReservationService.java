package com.repowr.airbnb.service;

import com.repowr.airbnb.dto.request.CreateRoom;
import com.repowr.airbnb.dto.request.ReserveRoom;
import com.repowr.airbnb.entity.BookingEntity;
import com.repowr.airbnb.entity.RoomEntity;
import com.repowr.airbnb.repository.BookingRepository;
import com.repowr.airbnb.repository.RoomRepository;
import com.repowr.airbnb.transformer.ReservationTransformer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ReservationTransformer reservationTransformer;

    public ResponseEntity createRoom(CreateRoom createRoom) {
        try {
            RoomEntity roomEntity =
                    roomRepository.save(reservationTransformer.createRoomDtoToRoomEntity(createRoom));
            return new ResponseEntity<>(roomEntity.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in create room: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getRoomById(Integer id) {
        Optional<RoomEntity> optionalRoomEntity = roomRepository.findById(id);
        // this is debatable, it'd either be a 200 with a blank body, or a 404, because this specific resource
        // doesn't exist, I went with 404
        return optionalRoomEntity.<ResponseEntity<Object>>map(roomEntity -> new ResponseEntity<>(reservationTransformer.roomEntityToRoomDto(roomEntity, true), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>("No room found", HttpStatus.NOT_FOUND));
    }

    public ResponseEntity reserveRoom(Integer roomId, ReserveRoom reserveRoom) {
        try {
            BookingEntity bookingEntity =
                    bookingRepository.save(reservationTransformer.reservationDtoToBookingEntity(roomId, reserveRoom));
            return new ResponseEntity<>(bookingEntity.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error in create customer: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity getAllReservations() {
        List<BookingEntity> bookingEntityList = bookingRepository.findAll();
        if(CollectionUtils.isEmpty(bookingEntityList)) {
            return new ResponseEntity<>("No reservations found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(reservationTransformer.bookingEntityListToReservations(bookingEntityList),
                HttpStatus.OK);
    }
}
