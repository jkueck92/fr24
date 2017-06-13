package de.blackstar.flightradar24;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Detail {

    @Getter
    @Setter
    private String schd_from;

    @Getter
    @Setter
    private String schd_to;

}
