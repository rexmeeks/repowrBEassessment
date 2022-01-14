package com.repowr.airbnb.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class Room {

    //I'm just going to use an integer id, in a real situation you'd want to do a random UUID
    private Integer id;
    private String name;
    private List<Day> bookedDays;
}
