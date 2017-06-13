package de.blackstar.database.service.airline;

import de.blackstar.database.entity.Airline;

public interface IAirlineService {

    Airline findByIata(final String iata);

    Airline findByIcao(final String icao);

}
