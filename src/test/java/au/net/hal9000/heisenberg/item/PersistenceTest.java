package au.net.hal9000.heisenberg.item;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.Test;

import au.net.hal9000.heisenberg.item.Factory;
import au.net.hal9000.heisenberg.item.Item;
import au.net.hal9000.heisenberg.util.Configuration;

public class PersistenceTest {

    @Test
    public void testJpaPersistence() {
        Configuration config = null;
        try {
            config = new Configuration("test/config/config.xml");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail(e.getStackTrace().toString());
        }
        Vector<String> itemClasses = config.getItemClasses();

        final String PERSISTENCE_UNIT_NAME = "items";
        EntityManagerFactory factory;

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        for (String itemClass : itemClasses) {

            // Create a new Item
            em.getTransaction().begin();
            Item item = Factory.createItem(itemClass);
//            System.out.println("The " + itemClass + " jpaID before="
//                    + item.getJpaId());
            item.setName("This is a " + itemClass + " Name");
            item.setDescription("This is a " + itemClass + " Description");
            
            // Persist it
            em.persist(item);
            em.getTransaction().commit();
            long jpaId = item.getJpaId();
//            System.out.println("The " + itemClass + " jpaID after=" + jpaId);

            Query q = em.createQuery("select c from " + itemClass
                    + " c where c.jpaId = " + jpaId);
            List<Item> itemList = q.getResultList();
            assertEquals("select count", 1, itemList.size());
            for (Item item2 : itemList) {
                assertEquals("name", "This is a " + itemClass + " Name",
                        item2.getName());
                assertEquals("description", "This is a " + itemClass
                        + " Description", item2.getDescription());
            }
        }
        em.close();
    }
}
