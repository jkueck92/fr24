package de.blackstar.database.service.aircraft.type;

import de.blackstar.database.entity.AircraftType;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AircraftTypeService extends AService<AircraftType> implements IAircraftTypeService {

    public AircraftTypeService(EntityManager entityManager) {
        super(entityManager);
    }

    public AircraftType findByCode(String code) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM AircraftType e WHERE e.code = :code");
        query.setParameter("code", code);
        return this.getSingleResult(query.getResultList());
    }

    public Class<AircraftType> getClazz() {
        return AircraftType.class;
    }

    public String getTableName() {
        return AircraftType.TABLE_NAME;
    }
}
