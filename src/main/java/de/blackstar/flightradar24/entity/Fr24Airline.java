package de.blackstar.flightradar24.entity;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Airline {

    @Getter
    @Setter
    @SerializedName("short")
    private String name;

    @Getter
    @Setter
    private Fr24Code code;

}
