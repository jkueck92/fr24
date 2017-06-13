package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Country {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

}
