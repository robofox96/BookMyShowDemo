package com.example.demo.apimodels;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class NewUserRequest {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

}
