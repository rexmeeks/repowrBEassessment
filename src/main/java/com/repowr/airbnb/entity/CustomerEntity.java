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
@Table(name = "customers")
public class CustomerEntity {

    public CustomerEntity(){}

    public CustomerEntity(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="user_id")
    private Integer userId;

    @Column(name="email")
    private String email;

    @Column(name="first_name", length = 150)
    private String firstName;

    @Column(name="last_name", length = 150)
    private String lastName;

    @OneToMany(mappedBy = "customerEntity", targetEntity = BookingEntity.class, cascade = CascadeType.REFRESH)
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "customerEntity", targetEntity = RoomEntity.class, cascade = CascadeType.REFRESH)
    private List<RoomEntity> rooms;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated")
    private Date updated;
}
