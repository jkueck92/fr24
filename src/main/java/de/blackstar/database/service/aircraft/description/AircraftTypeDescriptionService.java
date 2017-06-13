package de.blackstar.database.service.aircraft.description;

import de.blackstar.database.entity.AircraftTypeDescription;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AircraftTypeDescriptionService extends AService<AircraftTypeDescription> implements IAircraftTypeDescriptionService {

    public AircraftTypeDescriptionService(EntityManager entityManager) {
        super(entityManager);
    }

    public AircraftTypeDescription findByDescription(String description) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM AircraftTypeDescription e WHERE e.description = :description");
        query.setParameter("description", description);
        return this.getSingleResult(query.getResultList());
    }

    public Class<AircraftTypeDescription> getClazz() {
        return AircraftTypeDescription.class;
    }

    public String getTableName() {
        return AircraftTypeDescription.TABLE_NAME;
    }
}
