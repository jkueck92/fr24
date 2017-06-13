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
@Table(name = AircraftTypeDescription.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class AircraftTypeDescription extends AEntity {

    public final static String TABLE_NAME = "aircraftTypeDescription";

    @Getter
    @Setter
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_aircraftType_id")
    @Getter
    @Setter
    private AircraftType aircraftType;

}
