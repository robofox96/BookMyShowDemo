package com.example.demo.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pre_booking_locks", uniqueConstraints = @UniqueConstraint(columnNames = {"movie_schedule_id", "seat_id"}))
public class PreBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_schedule_id")
    private MovieSchedule movieSchedule;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

}
