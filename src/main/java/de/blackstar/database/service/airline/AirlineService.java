package de.blackstar.database.service.airline;

import de.blackstar.database.entity.Airline;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AirlineService extends AService<Airline> implements IAirlineService {

    public AirlineService(EntityManager entityManager) {
        super(entityManager);
    }

    public Airline findByIata(String iata) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Airline e WHERE e.iata = :iata");
        query.setParameter("iata", iata);
        return this.getSingleResult(query.getResultList());
    }

    @Deprecated
    public Airline findByIcao(String icao) {
        return null;
    }

    public Class<Airline> getClazz() {
        return Airline.class;
    }

    public String getTableName() {
        return Airline.TABLE_NAME;
    }
}
