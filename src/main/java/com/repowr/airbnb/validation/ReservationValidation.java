package com.repowr.airbnb.validation;

import com.repowr.airbnb.dto.request.CreateRoom;
import com.repowr.airbnb.dto.request.ReserveRoom;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

@Component
@Slf4j
public class ReservationValidation {

    // having these actually just throw a custom error would actually be more ideal

    public ResponseEntity validateReservationRequest(ReserveRoom reserveRoom) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = null;
        LocalDate endDate = null;
        try {
            startDate = LocalDate.parse(reserveRoom.getStartDate(), formatter);
        } catch(DateTimeParseException e) {
            log.error("Unable to parse start date: {}", e.getMessage());
            return new ResponseEntity<>("Start date should be in format yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

        try {
            endDate = LocalDate.parse(reserveRoom.getEndDate(), formatter);
        } catch(DateTimeParseException e) {
            log.error("Unable to parse end date: {}", e.getMessage());
            return new ResponseEntity<>("Start date should be in format yyyy-MM-dd", HttpStatus.BAD_REQUEST);
        }

        if(startDate.isBefore(LocalDate.now())) {
            return new ResponseEntity<>("Start Date needs to be in the future", HttpStatus.BAD_REQUEST);
        } else if(endDate.isBefore(startDate)) {
            return new ResponseEntity<>("End Date needs to be after start date", HttpStatus.BAD_REQUEST);
        }

        return null;
    }

    public ResponseEntity validateCreateRoomRequest(CreateRoom createRoom) {
        if(createRoom == null || createRoom.equals(new CreateRoom())) {
            return new ResponseEntity<>("Create room request is empty", HttpStatus.BAD_REQUEST);
        } else if(createRoom.getOwnerId() == null || createRoom.getOwnerId() < 0) {
            return new ResponseEntity<>("Owner Id is null or less than 0", HttpStatus.BAD_REQUEST);
        }
        else if(StringUtils.isBlank(createRoom.getAddress())) {
            return new ResponseEntity<>("Blank address", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createRoom.getName())) {
            return new ResponseEntity<>("Name is blank", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createRoom.getCity()) || createRoom.getCity().length() > 150) {
            return new ResponseEntity<>("City is blank or greater than 150 characters", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createRoom.getState()) || createRoom.getState().length() > 2) {
            return new ResponseEntity<>("State is blank or more than 2 characters", HttpStatus.BAD_REQUEST);
        } else if(StringUtils.isBlank(createRoom.getZipCode()) || createRoom.getZipCode().length() > 5) {
            return new ResponseEntity<>("Zip code is blank or more than 5 characters", HttpStatus.BAD_REQUEST);
        }

        return null;
    }
}
