package com.transire.appopenweathermap.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
public class Main {
    @SerializedName("temp")
    @Expose
    private Double temp;
}
