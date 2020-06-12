package com.example.demo.apimodels;

import com.example.demo.entities.Address;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class NewTheaterRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private Address address;

    @SerializedName("no_of_screens")
    private Integer noOfScreens;

}
