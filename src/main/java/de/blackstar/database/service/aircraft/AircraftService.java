package de.blackstar.database.service.aircraft;

import de.blackstar.database.entity.Aircraft;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AircraftService extends AService<Aircraft> implements IAircraftService {

    public AircraftService(EntityManager entityManager) {
        super(entityManager);
    }

    public Aircraft findByRegistration(String registration) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Aircraft e WHERE e.registration = :registration");
        query.setParameter("registration", registration);
        return this.getSingleResult(query.getResultList());
    }

    public Aircraft findByHex(String hex) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Aircraft e WHERE e.hex = :hex");
        query.setParameter("hex", hex);
        return this.getSingleResult(query.getResultList());
    }

    public Class<Aircraft> getClazz() {
        return Aircraft.class;
    }

    public String getTableName() {
        return Aircraft.TABLE_NAME;
    }
}
