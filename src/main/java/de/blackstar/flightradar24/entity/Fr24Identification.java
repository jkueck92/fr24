package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Identification {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private Fr24Number number;

    @Getter
    @Setter
    private String callsign;

}
