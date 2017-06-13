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
@Table(name = Flight.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Flight extends AEntity {

    public final static String TABLE_NAME = "flight";

    @Getter
    @Setter
    private String fr24Id;

    @Getter
    @Setter
    private String flightnumber;

    @Getter
    @Setter
    private String callsign;

    @ManyToOne
    @JoinColumn(name = "fk_origin_airport_id")
    @Getter
    @Setter
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "fk_destination_airport_id")
    @Getter
    @Setter
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "fk_airline_id")
    @Getter
    @Setter
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "fk_aircraft_id")
    @Getter
    @Setter
    private Aircraft aircraft;

}
