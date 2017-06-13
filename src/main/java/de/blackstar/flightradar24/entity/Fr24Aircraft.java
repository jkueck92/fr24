package de.blackstar.flightradar24.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24Aircraft {

    @Getter
    @Setter
    private String registration;

    @Getter
    @Setter
    private String hex;

    @Getter
    @Setter
    private Fr24Model model;

}
