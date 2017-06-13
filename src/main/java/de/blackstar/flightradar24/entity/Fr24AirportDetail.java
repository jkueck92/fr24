package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24AirportDetail {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Fr24Code code;

    @Getter
    @Setter
    private Fr24Position position;

}
