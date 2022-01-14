package com.repowr.airbnb.dto.request;

import lombok.Data;

@Data
public class Reservation {
    private String custId;
    private String id;
    private String startDate;
    private String endDate;
}
