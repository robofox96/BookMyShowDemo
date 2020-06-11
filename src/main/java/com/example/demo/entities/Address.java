package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    protected Integer id;

    @Column(name = "streetAddress")
    private String streetAdress;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

}
