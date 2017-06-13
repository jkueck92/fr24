package de.blackstar.flightradar24;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class Fr24FlightSearch {

    @Getter
    @Setter
    private List<Fr24FlightSearchDetail> results;

}
