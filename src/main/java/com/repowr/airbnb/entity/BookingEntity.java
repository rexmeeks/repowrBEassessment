package com.repowr.airbnb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class BookingEntity {

    public BookingEntity() {
    }

    public BookingEntity(LocalDate startDate, LocalDate endDate){
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(targetEntity = CustomerEntity.class, cascade = CascadeType.REFRESH)
    private CustomerEntity customerEntity;

    @JoinColumn(name="room_id", referencedColumnName = "room_id")
    @ManyToOne(targetEntity = RoomEntity.class, cascade = CascadeType.REFRESH)
    private RoomEntity roomEntity;

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
