package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Airport {


    @Getter
    @Setter
    private Fr24AirportDetail origin;

    @Getter
    @Setter
    private Fr24AirportDetail destination;

}
