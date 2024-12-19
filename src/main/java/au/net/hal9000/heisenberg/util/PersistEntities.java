package au.net.hal9000.heisenberg.util;

import au.net.hal9000.heisenberg.item.api.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.log4j.Logger;

public class PersistEntities {

  /** Persistence unit name for Entity Manager. */
  private static final String PERSISTENCE_UNIT_NAME = "items";

  private static final Logger LOGGER = Logger.getLogger(PersistEntities.class.getName());

  public static void save(final Item item) { // TODO List<Item> items

    /** Persistence Entity Manager. */
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    if (null == factory) {
      throw new RuntimeException("No JPA factory");
    }
    EntityManager entityManager = factory.createEntityManager();
    if (null == entityManager) {
      throw new RuntimeException("No JPA entity manager");
    }
    entityManager.getTransaction().begin();
    entityManager.persist(item);
    entityManager.getTransaction().commit();
    entityManager.close();
    factory.close();
    LOGGER.info("Saved");
  }

  public static <T> T find(Class<T> entityClass, Object primaryKey) {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    var entity = em.find(entityClass, primaryKey);
    em.close();
    factory.close();
    return entity;
  }
}
