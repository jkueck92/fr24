package de.blackstar.database.service.country;

import de.blackstar.database.entity.Country;
import de.blackstar.database.service.AService;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CountryService extends AService<Country> implements ICountryService {

    public CountryService(EntityManager entityManager) {
        super(entityManager);
    }

    public Country findByCode(String code) {
        Query query = this.getEntityManager().createQuery("SELECT e FROM Country e WHERE e.code = :code");
        query.setParameter("code", code);
        return this.getSingleResult(query.getResultList());
    }

    public Class<Country> getClazz() {
        return Country.class;
    }

    public String getTableName() {
        return Country.TABLE_NAME;
    }
}
