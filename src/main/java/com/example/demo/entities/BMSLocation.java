package com.example.demo.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BMSLocation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @SerializedName("address")
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
