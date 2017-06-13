package de.blackstar.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = AircraftType.TABLE_NAME)
@NoArgsConstructor
public class AircraftType extends AEntity {

    public final static String TABLE_NAME = "aircraftType";

    @Getter
    @Setter
    private String code;

    @OneToMany(mappedBy = "aircraftType")
    @Getter
    @Setter
    private List<AircraftTypeDescription> aircraftTypeDescriptions;

    public AircraftType(String code) {
        this.code = code;
    }

}
