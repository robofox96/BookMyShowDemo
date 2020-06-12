package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
public class NewScreenLayoutRequest {

    @SerializedName("screen_id")
    private Integer screenId;

    @SerializedName("layout")
    private List<TierDetails> tierDetailsList;

    @Data
    @ToString
    @AllArgsConstructor
    public static class TierDetails{
        @SerializedName("tier_name")
        private String tierName;

        @SerializedName("tier_price")
        private Integer tierPrice;

        @SerializedName("no_of_seats")
        private Integer noOfSeats;
    }

}
