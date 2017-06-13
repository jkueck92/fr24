package de.blackstar.database.service.aircraft;

import de.blackstar.database.entity.Aircraft;

public interface IAircraftService {

    Aircraft findByRegistration(final String registration);

    Aircraft findByHex(final String hex);

}
