package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.api.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.log4j.Logger;

/** Utility class for persisting and retrieving entities using JPA. */
public class PersistEntities {

  /** Persistence unit name for Entity Manager. */
  private static final String PERSISTENCE_UNIT_NAME = "items";

  private static final Logger LOGGER = Logger.getLogger(PersistEntities.class.getName());

  /**
   * Saves the given item to the database.
   *
   * @param item the item to be persisted
   */
  public static void save(final Item item) { // TODO List<Item> items

    // Persistence Entity Manager.
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    if (factory == null) {
      throw new RuntimeException("No JPA factory");
    }
    EntityManager entityManager = factory.createEntityManager();
    if (entityManager == null) {
      throw new RuntimeException("No JPA entity manager");
    }
    entityManager.getTransaction().begin();
    entityManager.persist(item);
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();
    LOGGER.info("Saved");
  }

  /**
   * Finds an entity of the specified class by its primary key.
   *
   * @param <T> the type of the entity
   * @param entityClass the class of the entity to find
   * @param primaryKey the primary key of the entity
   * @return the found entity or null if no entity is found
   */
  public static <T> T find(Class<T> entityClass, Object primaryKey) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    var entity = em.find(entityClass, primaryKey);
    em.close();
    factory.close();
    return entity;
  }
}
