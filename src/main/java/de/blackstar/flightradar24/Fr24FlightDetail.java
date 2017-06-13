package de.blackstar.flightradar24;

import de.blackstar.flightradar24.entity.Fr24Aircraft;
import de.blackstar.flightradar24.entity.Fr24Airline;
import de.blackstar.flightradar24.entity.Fr24Airport;
import de.blackstar.flightradar24.entity.Fr24Identification;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Fr24FlightDetail {

    @Getter
    @Setter
    private Fr24Identification identification;

    @Getter
    @Setter
    private Fr24Aircraft aircraft;

    @Getter
    @Setter
    private Fr24Airline airline;

    @Getter
    @Setter
    private Fr24Airport airport;

    public boolean checkIsNull() {
        if (this.identification != null && this.aircraft != null && this.airline != null && this.airport != null) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

}
