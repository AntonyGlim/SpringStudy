package glim.antony.hibernate.crud;

import glim.antony.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CrudApp {
    public static void main(String[] args) throws Exception {
        PrepareDataApp.forcePrepareData();

        Long maxId = null;

        SessionFactory factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;
        try {
            System.out.println("============\n== CREATE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            SimpleItem newSimpleItem = new SimpleItem("Chair", 1000);
            System.out.println("Before save: " + newSimpleItem);
            session.save(newSimpleItem);
            System.out.println("After save: " + newSimpleItem);
            session.getTransaction().commit();
            System.out.println("After save and commit: " + newSimpleItem);

            System.out.println("============\n=== READ ===\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            SimpleItem simpleItemFromDb = session.get(SimpleItem.class, 1L);
            System.out.println(simpleItemFromDb);
            session.getTransaction().commit();

            System.out.println("============\n== UPDATE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            maxId = session.createQuery("SELECT MAX(s.id) FROM SimpleItem s", Long.class).getSingleResult();
            SimpleItem simpleItemForUpdate = session.createQuery("SELECT s FROM SimpleItem s WHERE s.id = :id", SimpleItem.class)
                    .setParameter("id", maxId)
                    .getSingleResult();
            System.out.println("Loaded item with max(id): " + simpleItemForUpdate);
            simpleItemForUpdate.setPrice(simpleItemForUpdate.getPrice() + 100);
            System.out.println("Modified item: " + simpleItemForUpdate);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            SimpleItem simpleItemAfterUpdate = session.get(SimpleItem.class, simpleItemForUpdate.getId());
            System.out.println("Loaded item after update: " + simpleItemAfterUpdate);
            session.getTransaction().commit();

            System.out.println("============\n== DELETE ==\n============");
            session = factory.getCurrentSession();
            session.beginTransaction();
            maxId = session.createQuery("SELECT MAX(s.id) FROM SimpleItem s", Long.class).getSingleResult();
            session.delete(session.get(SimpleItem.class, maxId));
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            SimpleItem removedSimpleItem = session.get(SimpleItem.class, maxId);
            System.out.println("Loaded item after remove: " + removedSimpleItem);
            session.getTransaction().commit();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
