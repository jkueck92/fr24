package de.blackstar.flightradar24.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Number {

    @Getter
    @Setter
    @SerializedName("default")
    private String flightnumber;

}
