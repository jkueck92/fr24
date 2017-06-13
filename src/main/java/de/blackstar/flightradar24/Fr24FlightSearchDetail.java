package de.blackstar.flightradar24;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24FlightSearchDetail {

    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private Fr24Detail detail;

}
