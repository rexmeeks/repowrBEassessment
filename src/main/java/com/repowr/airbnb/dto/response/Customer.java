package com.repowr.airbnb.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private Integer id;

    private String email;

    private String firstName;

    private String lastName;

}
