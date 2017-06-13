package de.blackstar.database.service.aircraft.type;

import de.blackstar.database.entity.AircraftType;

public interface IAircraftTypeService {

    AircraftType findByCode(final String code);

}
