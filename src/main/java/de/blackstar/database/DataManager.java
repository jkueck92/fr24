package de.blackstar.database;

import de.blackstar.database.entity.*;
import de.blackstar.database.service.aircraft.AircraftService;
import de.blackstar.database.service.aircraft.description.AircraftTypeDescriptionService;
import de.blackstar.database.service.aircraft.type.AircraftTypeService;
import de.blackstar.database.service.airline.AirlineService;
import de.blackstar.database.service.airport.AirportService;
import de.blackstar.database.service.country.CountryService;
import de.blackstar.database.service.flight.FlightService;
import de.blackstar.flightradar24.Fr24FlightDetail;
import de.blackstar.flightradar24.entity.Fr24Aircraft;
import de.blackstar.flightradar24.entity.Fr24Airline;
import de.blackstar.flightradar24.entity.Fr24AirportDetail;
import de.blackstar.flightradar24.entity.Fr24Country;
import org.apache.log4j.Logger;

public class DataManager {

    private static final Logger LOGGER = Logger.getLogger(DataManager.class);

    private ServiceFactory serviceFactory;

    public DataManager() {
        this.serviceFactory = ServiceFactory.getInstance();
    }

    public void manageData(final Fr24FlightDetail fr24FlightDetail) {
        this.checkFlight(fr24FlightDetail);
    }

    private Flight checkFlight(final Fr24FlightDetail fr24FlightDetail) {
        FlightService fs = this.serviceFactory.getFlightService();
        if (fs.findByFr24Id(fr24FlightDetail.getIdentification().getId()) == null) {
            LOGGER.debug("FLIGHT [" + fr24FlightDetail.getIdentification().getId() + "] NOT EXIST, CREATE NEW!");
            fs.save(new Flight(
                    fr24FlightDetail.getIdentification().getId(),
                    fr24FlightDetail.getIdentification().getNumber().getFlightnumber(),
                    fr24FlightDetail.getIdentification().getCallsign(),
                    this.checkAirport(fr24FlightDetail.getAirport().getOrigin()),
                    this.checkAirport(fr24FlightDetail.getAirport().getDestination()),
                    this.checkAirline(fr24FlightDetail.getAirline()),
                    this.checkAircraft(fr24FlightDetail.getAircraft())
            ));
        }
        return this.findFlightByFr24Id(fr24FlightDetail.getIdentification().getId());
    }

    private Airport checkAirport(final Fr24AirportDetail fr24AirportDetail) {
        AirportService as = this.serviceFactory.getAirportService();
        if (as.findByIata(fr24AirportDetail.getCode().getIata()) == null) {
            LOGGER.debug("AIRPORT [" + fr24AirportDetail.getCode().getIata() + "] NOT EXIST, CREATE NEW!");
            as.save(new Airport(
                    fr24AirportDetail.getName(),
                    fr24AirportDetail.getCode().getIata(),
                    fr24AirportDetail.getCode().getIcao(),
                    fr24AirportDetail.getPosition().getLatitude(),
                    fr24AirportDetail.getPosition().getLongitude(),
                    fr24AirportDetail.getPosition().getAltitude(),
                    this.checkCountry(fr24AirportDetail.getPosition().getCountry())));
        }
        return this.findAirportByIata(fr24AirportDetail.getCode().getIata());
    }

    private Aircraft checkAircraft(final Fr24Aircraft fr24Aircraft) {
        AircraftService as = this.serviceFactory.getAircraftService();
        if (as.findByHex(fr24Aircraft.getHex()) == null) {
            LOGGER.debug("AIRCRAFT [" + fr24Aircraft.getHex() + "] NOT EXIST, CREATE NEW!");
            as.save(new Aircraft(
                    fr24Aircraft.getRegistration(),
                    fr24Aircraft.getHex(),
                    this.checkAircraftType(fr24Aircraft),
                    this.checkAircraftTypeDescription(fr24Aircraft)));
        }
        return this.findAircraftByHex(fr24Aircraft.getHex());
    }

    private AircraftType checkAircraftType(final Fr24Aircraft fr24Aircraft) {
        AircraftTypeService ats = this.serviceFactory.getAircraftTypeService();
        if (ats.findByCode(fr24Aircraft.getModel().getCode()) == null) {
            LOGGER.debug("AIRCRAFT_TYPE [" + fr24Aircraft.getModel().getCode() + "] NOT EXIST, CREATE NEW!");
            ats.save(new AircraftType(fr24Aircraft.getModel().getCode()));
        }
        return this.findAircraftTypeByCode(fr24Aircraft.getModel().getCode());
    }

    private AircraftTypeDescription checkAircraftTypeDescription(final Fr24Aircraft fr24Aircraft) {
        AircraftTypeDescriptionService atds = this.serviceFactory.getAircraftTypeDescriptionService();
        if (atds.findByDescription(fr24Aircraft.getModel().getText()) == null) {
            LOGGER.debug("AIRCRAFT_TYPE_DESCRIPTION [" + fr24Aircraft.getModel().getText() + "] NOT EXIST, CREATE NEW!");
            atds.save(new AircraftTypeDescription(fr24Aircraft.getModel().getText(), this.checkAircraftType(fr24Aircraft)));
        }
        return this.findAircraftTypeDescriptionByDescription(fr24Aircraft.getModel().getText());
    }

    private Country checkCountry(final Fr24Country fr24Country) {
        CountryService cs = this.serviceFactory.getCountryService();
        if (cs.findByCode(fr24Country.getCode()) == null) {
            LOGGER.debug("COUNTRY [" + fr24Country.getCode() + "] NOT EXIST, CREATE NEW!");
            cs.save(new Country(fr24Country.getName(), fr24Country.getCode()));
        }
        return this.findCountryByCode(fr24Country.getCode());
    }

    private Airline checkAirline(final Fr24Airline fr24Airline) {
        AirlineService as = this.serviceFactory.getAirlineService();
        if (as.findByIata(fr24Airline.getCode().getIata()) == null) {
            LOGGER.debug("AIRLINE [" + fr24Airline.getCode().getIata() + "] NOT EXIST, CREATE NEW!");
            as.save(new Airline(fr24Airline.getName(), fr24Airline.getCode().getIata(), fr24Airline.getCode().getIcao()));
        }
        return this.findAirlineByIata(fr24Airline.getCode().getIata());
    }

    public boolean existFlightWithFr24Id(final String fr24Id) {
        if (this.findFlightByFr24Id(fr24Id) != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Flight findFlightByFr24Id(final String fr24Id) {
        return this.serviceFactory.getFlightService().findByFr24Id(fr24Id);
    }

    private Airport findAirportByIata(final String iata) {
        return this.serviceFactory.getAirportService().findByIata(iata);
    }

    private Aircraft findAircraftByHex(final String hex) {
        return this.serviceFactory.getAircraftService().findByHex(hex);
    }

    private AircraftType findAircraftTypeByCode(final String code) {
        return this.serviceFactory.getAircraftTypeService().findByCode(code);
    }

    private AircraftTypeDescription findAircraftTypeDescriptionByDescription(final String description) {
        return this.serviceFactory.getAircraftTypeDescriptionService().findByDescription(description);
    }

    private Country findCountryByCode(final String code) {
        return this.serviceFactory.getCountryService().findByCode(code);
    }

    private Airline findAirlineByIata(final String iata) {
        return this.serviceFactory.getAirlineService().findByIata(iata);
    }

}
