package com.ptc.assignment.junit.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DBManager {
    
    private EntityManagerFactory entityManagerFactory;

    public DBManager() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
    }

    public <JPAEntity> JPAEntity merge(JPAEntity entityToMerge) {
        JPAEntity ret = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            ret = entityManager.merge(entityToMerge);
            entityTransaction.commit();
        } catch (Exception e) {
            handleDatabaseException(e);
        } finally {
            entityManager.close();
        }
        return ret;
    }


    public <JPAEntity> JPAEntity persist(JPAEntity entityToPersist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entityToPersist);
            entityTransaction.commit();
        } catch (Exception e) {
            handleDatabaseException(e);
        } finally {
            entityManager.close();
        }
        return entityToPersist;
    }

    public <JPAEntity> JPAEntity find(Class<JPAEntity> entityClass, Object key) {
        JPAEntity ret = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            ret = entityManager.find(entityClass, key);
        } catch (Exception e) {
            handleDatabaseException(e);
        } finally {
            entityManager.close();
        }
        return ret;
    }

    public <JPAEntity> List<JPAEntity> findAll(Class<JPAEntity> entityClass) {
        List<JPAEntity> ret = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            ret = entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e").getResultList();
        } catch (Exception e) {
            handleDatabaseException(e);
        } finally {
            entityManager.close();
        }
        return ret;
    }

    public <JPAEntity> void remove(Class<JPAEntity> entityClass, Object key) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            JPAEntity entityToRemove = entityManager.find(entityClass, key);
            if (entityToRemove != null) {
                entityManager.getTransaction().begin();
                entityManager.remove(entityToRemove);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            handleDatabaseException(e);
        } finally {
            entityManager.close();
        }
    }

    private static void handleDatabaseException(Exception exception) {
        Throwable originalExceptionCause = exception.getCause();
        if (originalExceptionCause != null) {
            throw new RuntimeException(exception.getCause());
        }
        throw new RuntimeException(exception);
    }
}
