package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class NewMovieRequestModel {

    @SerializedName("name")
    private String name;

    @SerializedName("genre")
    private String genre;

    @SerializedName("language")
    private String language;

    @SerializedName("duration")
    private Integer duration;

    @SerializedName("rating")
    private Integer rating;

}
