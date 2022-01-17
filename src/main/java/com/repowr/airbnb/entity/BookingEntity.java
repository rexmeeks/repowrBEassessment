package com.repowr.airbnb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

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

    @JoinColumn(name="room_id")
    @ManyToOne(targetEntity = RoomEntity.class, cascade = CascadeType.REFRESH)
    private RoomEntity roomEntity;

    @Column(name="room_id", insertable = false, updatable = false, nullable = false)
    private Integer roomId;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate endDate;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated")
    private Date updated;
}
