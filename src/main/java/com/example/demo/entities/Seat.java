package com.example.demo.entities;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @SerializedName("screen")
    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @SerializedName("tier")
    @ManyToOne
    @JoinColumn(name = "tier_id")
    private Tier tier;

    @SerializedName("status")
    @Column(name = "status", columnDefinition = "enum('BOOKED','EMPTY','LOCKED')")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    public enum Status{
        BOOKED,EMPTY,LOCKED;
    }

}
