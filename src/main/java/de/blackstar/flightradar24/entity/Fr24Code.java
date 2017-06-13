package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Code {

    @Getter
    @Setter
    private String iata;

    @Getter
    @Setter
    private String icao;

}
