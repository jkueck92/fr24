package de.blackstar.database.service.airport;

import de.blackstar.database.entity.Airport;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AirportService extends AService<Airport> implements IAirportService {

    public AirportService(EntityManager entityManager) {
        super(entityManager);
    }

    public Airport findByIata(String iata) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Airport e WHERE e.iata = :iata");
        query.setParameter("iata", iata);
        return this.getSingleResult(query.getResultList());
    }

    public Airport findByIcao(String icao) {
        return null;
    }

    public Class<Airport> getClazz() {
        return Airport.class;
    }

    public String getTableName() {
        return Airport.TABLE_NAME;
    }
}
