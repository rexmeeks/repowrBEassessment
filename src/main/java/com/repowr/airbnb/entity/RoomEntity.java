package com.repowr.airbnb.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name="owner_id", insertable = false, updatable = false, nullable = false)
    private Integer ownerId;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="city", nullable = false, length = 50)
    private String city;

    @Column(name="state", nullable = false, length = 2)
    private String state;

    @Column(name="created", nullable = false)
    private LocalDate created;

    @Column(name="updated")
    private LocalDate updated;
}
