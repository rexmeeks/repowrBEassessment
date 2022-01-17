package com.repowr.airbnb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(name="id")
    @OneToOne(targetEntity = CustomerEntity.class, cascade = CascadeType.REFRESH)
    private CustomerEntity customerEntity;

    @JoinColumn(name="room_id")
    @OneToMany(targetEntity = BookingEntity.class, cascade = CascadeType.REFRESH)
    private Set<BookingEntity> bookings;

    @Column(name="owner_id", insertable = false, updatable = false, nullable = false)
    private Integer ownerId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="city", nullable = false, length = 50)
    private String city;

    @Column(name="state", nullable = false, length = 2)
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated")
    private Date updated;
}
