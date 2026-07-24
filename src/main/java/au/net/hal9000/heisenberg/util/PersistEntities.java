package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.api.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.extern.log4j.Log4j2;

/** Utility class for persisting and retrieving entities using JPA. */
@Log4j2
public class PersistEntities {

  /** Persistence unit name for Entity Manager. */
  private static final String PERSISTENCE_UNIT_NAME = "items";
  private static final EntityManagerFactory FACTORY =
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

  public static void save(final Item item) {
    EntityManager entityManager = FACTORY.createEntityManager();
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(item);
      entityManager.getTransaction().commit();
      log.info("Saved");
    } finally {
      if (entityManager.getTransaction().isActive()) {
        entityManager.getTransaction().rollback();
      }
      entityManager.close();
    }
  }

  public static <T> T find(Class<T> entityClass, Object primaryKey) {
    EntityManager em = FACTORY.createEntityManager();
    try {
      return em.find(entityClass, primaryKey);
    } finally {
      em.close();
    }
  }
}
