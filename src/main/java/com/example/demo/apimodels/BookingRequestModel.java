package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class BookingRequestModel {

    @SerializedName("user_id")
    private Integer userId;

    @SerializedName("movie_schedule_id")
    private Integer movieScheduleId;

    @SerializedName("seats")
    private List<Integer> seatIdList;

}
