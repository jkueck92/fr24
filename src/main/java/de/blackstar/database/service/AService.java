package de.blackstar.database.service;

import de.blackstar.database.entity.AEntity;
import lombok.AccessLevel;
import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

public abstract class AService<T extends AEntity> {

    @Getter(AccessLevel.PROTECTED)
    private EntityManager entityManager;

    public AService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<T> getClazz();

    public abstract String getTableName();

    public void save(final T obj) {
        this.entityManager.getTransaction().begin();
        obj.setInsertTimestamp(new Date());
        this.entityManager.persist(obj);
        this.entityManager.getTransaction().commit();
    }

    public void update(final T obj) {
        this.entityManager.getTransaction().begin();
        obj.setUpdateTimestamp(new Date());
        this.entityManager.merge(obj);
        this.entityManager.getTransaction().commit();
    }

    public void delete(final T obj) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(obj);
        this.entityManager.getTransaction().commit();
    }

    public T findById(final long id) {
        return this.entityManager.find(this.getClazz(), id);
    }

    public List<T> findAll() {
        Query query = this.entityManager.createQuery("SELECT e FROM " + this.getTableName() + " e");
        return query.getResultList();
    }

    protected T getSingleResult(final List<T> list) {
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


}
