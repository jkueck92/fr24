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
@Table(name = Aircraft.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft extends AEntity {

    public final static String TABLE_NAME = "aircraft";

    @Getter
    @Setter
    private String registration;

    @Getter
    @Setter
    private String hex;

    @ManyToOne
    @JoinColumn(name = "fk_aircraftType_id")
    @Getter
    @Setter
    private AircraftType aircraftType;

    @ManyToOne
    @JoinColumn(name = "fk_aircraftTypeDescription_id")
    @Getter
    @Setter
    private AircraftTypeDescription aircraftTypeDescription;

}
