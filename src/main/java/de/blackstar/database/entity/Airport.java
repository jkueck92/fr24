package de.blackstar.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = Airport.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Airport extends AEntity {

    public final static String TABLE_NAME = "airport";

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String iata;

    @Getter
    @Setter
    private String icao;

    @Getter
    @Setter
    private double latitude;

    @Getter
    @Setter
    private double longitude;

    @Getter
    @Setter
    private int altitude;

    @ManyToOne
    @JoinColumn(name = "fk_country_id")
    @Getter
    @Setter
    private Country country;

}
