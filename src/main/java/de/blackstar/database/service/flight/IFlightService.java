package de.blackstar.database.service.flight;

import de.blackstar.database.entity.Flight;

import java.util.List;

public interface IFlightService {

    Flight findByFr24Id(final String fr24Id);

    List<Flight> findByCallsign(final String callsign);

    List<Flight> findByFlightnumber(final String flightnumber);

}
