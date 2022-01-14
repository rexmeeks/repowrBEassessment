package com.repowr.airbnb.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="first_name", length = 50)
    private String firstName;

    @Column(name="last_name", length = 50)
    private String lastName;

    @Column(name="created")
    private LocalDate created;

    @Column(name="updated")
    private LocalDate updated;
}
