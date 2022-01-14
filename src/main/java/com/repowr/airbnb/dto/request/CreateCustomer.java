package com.repowr.airbnb.dto.request;

import lombok.Data;

@Data
public class CreateCustomer {

    private String email;

    private String firstName;

    private String lastName;
}
