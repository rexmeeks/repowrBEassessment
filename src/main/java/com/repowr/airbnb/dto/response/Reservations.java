package com.repowr.airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Reservations {
    private List<Day> bookedDays;
}
