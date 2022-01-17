package com.repowr.airbnb.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="first_name", length = 150)
    private String firstName;

    @Column(name="last_name", length = 150)
    private String lastName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="created")
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name="updated")
    private Date updated;
}
