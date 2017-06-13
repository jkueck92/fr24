package de.blackstar.database.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Airline.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Airline extends AEntity {

    public final static String TABLE_NAME = "airline";

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String iata;

    @Getter
    @Setter
    private String icao;

}
