package de.blackstar.database.service.aircraft.description;

import de.blackstar.database.entity.AircraftTypeDescription;

public interface IAircraftTypeDescriptionService {

    AircraftTypeDescription findByDescription(final String description);

}
