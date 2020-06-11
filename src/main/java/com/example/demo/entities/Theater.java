package com.example.demo.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "theaters")
@Data
public class Theater extends BMSLocation {

    @Column(name = "no_of_screens")
    private Integer noOfScreens;

    @OneToMany(mappedBy = "theater")
    private List<Screen> screenList;

}
