package com.repowr.airbnb.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name="id")
    @OneToOne(targetEntity = CustomerEntity.class, cascade = CascadeType.REFRESH)
    private CustomerEntity customerEntity;

    @Column(name="cust_id", insertable = false, updatable = false, nullable = false)
    private Integer custId;

    @JoinColumn(name="id")
    @OneToOne(targetEntity = RoomEntity.class, cascade = CascadeType.REFRESH)
    private RoomEntity roomEntity;

    @Column(name="room_id", insertable = false, updatable = false, nullable = false)
    private Integer roomId;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Column(name="created", nullable = false)
    private LocalDate created;

    @Column(name="updated")
    private LocalDate updated;
}
