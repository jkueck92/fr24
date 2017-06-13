package de.blackstar.database;

import de.blackstar.database.service.aircraft.AircraftService;
import de.blackstar.database.service.aircraft.description.AircraftTypeDescriptionService;
import de.blackstar.database.service.aircraft.type.AircraftTypeService;
import de.blackstar.database.service.airline.AirlineService;
import de.blackstar.database.service.airport.AirportService;
import de.blackstar.database.service.country.CountryService;
import de.blackstar.database.service.flight.FlightService;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServiceFactory {

    private static ServiceFactory instance;

    @Getter
    private CountryService countryService;

    @Getter
    private AirportService airportService;

    @Getter
    private FlightService flightService;

    @Getter
    private AirlineService airlineService;

    @Getter
    private AircraftService aircraftService;

    @Getter
    private AircraftTypeService aircraftTypeService;

    @Getter
    private AircraftTypeDescriptionService aircraftTypeDescriptionService;

    private ServiceFactory() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        this.countryService = new CountryService(entityManager);
        this.airportService = new AirportService(entityManager);
        this.flightService = new FlightService(entityManager);
        this.airlineService = new AirlineService(entityManager);
        this.aircraftService = new AircraftService(entityManager);
        this.aircraftTypeService = new AircraftTypeService(entityManager);
        this.aircraftTypeDescriptionService = new AircraftTypeDescriptionService(entityManager);
    }

    public static ServiceFactory getInstance() {
        if (ServiceFactory.instance == null) {
            ServiceFactory.instance = new ServiceFactory();
        }
        return ServiceFactory.instance;
    }

}
