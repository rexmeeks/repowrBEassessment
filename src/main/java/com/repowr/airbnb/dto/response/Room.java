package com.repowr.airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Room {

    //I'm just going to use an integer id, in a real situation you'd want to do a random UUID
    private Integer id;
    private Customer bookingCustomer;
    private String name;
    private Customer owner;
    private String address;
    private String city;
    private String state;
    private List<Day> bookedDays;
}
