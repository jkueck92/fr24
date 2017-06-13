package de.blackstar.database.service.airport;

import de.blackstar.database.entity.Airport;

public interface IAirportService {

    Airport findByIata(final String iata);

    Airport findByIcao(final String icao);

}
