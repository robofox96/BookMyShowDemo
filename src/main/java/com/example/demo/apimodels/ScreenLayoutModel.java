package com.example.demo.apimodels;

import com.example.demo.entities.Seat;
import com.example.demo.entities.Tier;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@ToString
public class ScreenLayoutModel {

    @SerializedName("screen_id")
    private Integer screen_id;

    @SerializedName("screen_layout")
    private List<TierLayout> screenLayout;

    @SerializedName("screen_type")
    private String screenType;

    @Data
    @ToString
    @AllArgsConstructor
    public static class TierLayout{
        @SerializedName("tier")
        private Tier tier;

        @SerializedName("seat_list")
        private List<Seat> seatList;
    }

}
