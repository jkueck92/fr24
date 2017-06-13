package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Model {

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String text;

}
