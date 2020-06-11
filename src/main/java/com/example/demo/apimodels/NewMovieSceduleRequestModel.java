package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@ToString
public class NewMovieSceduleRequestModel {

    @SerializedName("movie_id")
    private Integer movieId;

    @SerializedName("theater_id")
    private Integer theater_id;

    @SerializedName("screen_id")
    private Integer screen_id;

    @SerializedName("start_time")
    private Date startTime;

    @SerializedName("end_time")
    private Date endTime;
}
