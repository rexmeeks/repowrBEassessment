package com.repowr.airbnb.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Reservations {
    private List<Day> bookedDays;
}
