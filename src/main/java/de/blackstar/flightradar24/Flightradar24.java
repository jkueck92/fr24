package de.blackstar.flightradar24;

import com.google.gson.Gson;
import de.blackstar.flightradar24.entity.*;
import de.blackstar.web.WebUtils;
import org.apache.log4j.Logger;

public class Flightradar24 {

    public static String FR24_SEARCH_URL = "https://www.flightradar24.com/v1/search/web/find?query=";
    public static String FR24_FLIGHT_DETAIL_URL = "https://data-live.flightradar24.com/clickhandler/?version=1.5&flight=";

    private static final Logger LOGGER = Logger.getLogger(Flightradar24.class);

    public Fr24FlightDetail getFr24FlightDetail(final String url) {
        LOGGER.debug("SEARCH_FD: " + url);
        String fr24FlightDetailJson = WebUtils.httpGet(url);
        if (fr24FlightDetailJson != null) {
            Fr24FlightDetail flightDetail = this.getFr24FlightDetailFromJson(fr24FlightDetailJson);
            if (!flightDetail.checkIsNull()) {
                if (this.checkFr24Identification(flightDetail.getIdentification()) && this.checkFr24Aircraft(flightDetail.getAircraft()) && this.checkFr24Airline(flightDetail.getAirline()) && this.checkFr24Airport(flightDetail.getAirport())) {
                    LOGGER.debug("  FOUND_FD: " + flightDetail);
                    return flightDetail;
                }
            }
        }
        LOGGER.debug("FOUND_NO_FD");
        return null;
    }

    public Fr24FlightSearchDetail getFr24FlightSearchDetailFromJson(final String url) {
        LOGGER.debug("SEARCH_FI: " + url);
        String fr24FlightSearchJson = WebUtils.httpGet(url);
        if (fr24FlightSearchJson != null) {
            Fr24FlightSearch fr24FlightSearch = this.getFr24FlightSearchFromJson(fr24FlightSearchJson);
            if (!fr24FlightSearch.getResults().isEmpty()) {
                for (Fr24FlightSearchDetail searchDetail : fr24FlightSearch.getResults()) {
                    if (searchDetail.getId() != null && searchDetail.getType() != null) {
                        if (searchDetail.getType().equals("live")) {
                            if (searchDetail.getDetail().getSchd_from() != null && searchDetail.getDetail().getSchd_to() != null) {
                                LOGGER.debug("  FOUND_FI: " + searchDetail);
                                return searchDetail;
                            }
                        }
                    }
                }
            }
        }
        LOGGER.debug("FOUND_NO_FI");
        return null;
    }

    private Fr24FlightDetail getFr24FlightDetailFromJson(final String json) {
        return new Gson().fromJson(json, Fr24FlightDetail.class);
    }

    private Fr24FlightSearch getFr24FlightSearchFromJson(final String json) {
        return new Gson().fromJson(json, Fr24FlightSearch.class);
    }

    private boolean checkFr24Identification(final Fr24Identification identification) {
        if (identification.getCallsign() != null && identification.getNumber() != null) {
            if (identification.getNumber().getFlightnumber() != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean checkFr24Aircraft(final Fr24Aircraft aircraft) {
        if (aircraft.getHex() != null && aircraft.getRegistration() != null && aircraft.getModel() != null) {
            if (aircraft.getModel().getCode() != null & aircraft.getModel().getText() != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean checkFr24Airline(final Fr24Airline airline) {
        if (airline.getName() != null && airline.getCode() != null) {
            if (airline.getCode().getIata() != null && airline.getCode().getIcao() != null) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean checkFr24Airport(final Fr24Airport airport) {
        if (airport.getOrigin() != null && airport.getDestination() != null) {
            if (this.checkFr24AirportDetail(airport.getOrigin()) && this.checkFr24AirportDetail(airport.getDestination())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    private boolean checkFr24AirportDetail(final Fr24AirportDetail airportDetail) {
        boolean tmp = Boolean.FALSE;
        if (airportDetail.getName() != null && airportDetail.getCode() != null && airportDetail.getPosition() != null) {
            tmp = Boolean.TRUE;
            if (airportDetail.getCode().getIata() != null && airportDetail.getCode().getIcao() != null) {
                tmp = Boolean.TRUE;
            }
            if (airportDetail.getPosition().getCountry() != null) {
                if (airportDetail.getPosition().getCountry().getName() != null && airportDetail.getPosition().getCountry().getCode() != null) {
                    tmp = Boolean.TRUE;
                }
            }

        }
        return tmp;
    }

}
