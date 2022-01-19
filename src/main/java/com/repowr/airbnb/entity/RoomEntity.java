package com.repowr.airbnb.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rooms")
public class RoomEntity {

    public RoomEntity() {
    }

    public RoomEntity(String name, String address, String city, String state, String zipCode) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "roomEntity", targetEntity = BookingEntity.class, cascade = CascadeType.REFRESH)
    private List<BookingEntity> bookings;


    @Column(name="name", nullable = false)
    private String name;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="city", nullable = false, length = 50)
    private String city;

    @Column(name="state", nullable = false, length = 2)
    private String state;

    @Column(name="zip_code", nullable = false, length = 5)
    private String zipCode;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated")
    private Date updated;
}
