package com.repowr.airbnb.dto.request;

import lombok.Data;

@Data
public class CreateRoom {

    private Integer ownerId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zipCode;

}
