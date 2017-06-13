package de.blackstar.database.service.flight;

import de.blackstar.database.entity.Flight;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class FlightService extends AService<Flight> implements IFlightService {

    public FlightService(EntityManager entityManager) {
        super(entityManager);
    }

    public Flight findByFr24Id(String fr24Id) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Flight e WHERE e.fr24Id = :fr24Id");
        query.setParameter("fr24Id", fr24Id);
        return this.getSingleResult(query.getResultList());
    }

    public List<Flight> findByCallsign(String callsign) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Flight e WHERE e.callsign = :callsign");
        query.setParameter("callsign", callsign);
        return query.getResultList();
    }

    public List<Flight> findByFlightnumber(String flightnumber) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Flight e WHERE e.flightnumber = :flightnumber");
        query.setParameter("flightnumber", flightnumber);
        return query.getResultList();
    }

    public Class<Flight> getClazz() {
        return Flight.class;
    }

    public String getTableName() {
        return Flight.TABLE_NAME;
    }
}
