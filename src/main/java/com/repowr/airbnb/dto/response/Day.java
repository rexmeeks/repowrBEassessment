package com.repowr.airbnb.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class Day {
    private LocalDate day;
    private List<Room> rooms;

    // just for the sake of keeping things simpler we'll have a booked field here for when a room needs to return
    // what days it's booked for. This will be useful if a customer is wanting to stay at a specific room
    private Boolean booked;
}
