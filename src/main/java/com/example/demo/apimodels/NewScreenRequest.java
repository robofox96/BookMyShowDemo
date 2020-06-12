package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class NewScreenRequest {

    @SerializedName("theater_id")
    private Integer theaterId;

    @SerializedName("screen_type")
    private String screenType;

}
