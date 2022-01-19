package com.repowr.airbnb.dto.request;

import lombok.Data;

@Data
public class ReserveRoom {
    private Integer custId;
    private String startDate;
    private String endDate;
}
