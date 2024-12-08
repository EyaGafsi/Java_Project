package org.example.demo1.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;


public class GenericDAO<T> {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjectJaveUnity");
    private Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    public void add(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); // Démarrage de la transaction
            em.persist(entity); // Persistance de l'entité
            em.getTransaction().commit(); // Validation de la transaction
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // En cas d'erreur, rollback
            }
            e.printStackTrace();
        } finally {
            em.close(); // Libérer les ressources
        }
    }

    public T getById(Object id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public T update(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return entity;
        } finally {
            em.close();
        }
    }

    public void delete(T entity) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            if (entity != null) {
                // Vérification de l'existence de l'entité dans la base de données
                if (!em.contains(entity)) {
                    entity = em.merge(entity);  // Si l'entité n'est pas attachée au contexte de persistance, on fait une fusion
                }

                em.remove(entity);  // Supprimer l'entité
            } else {
                System.out.println("L'entité est null, impossible de la supprimer.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();  // Annulation de la transaction en cas d'exception
            e.printStackTrace();  // Pour l'instant, on affiche l'exception dans la sortie d'erreur
        } finally {
            em.close();  // Fermer l'EntityManager
        }
    }



    public List<T> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        } finally {
            em.close();
        }
    }
}
