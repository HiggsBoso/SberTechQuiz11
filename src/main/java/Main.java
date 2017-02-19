import com.sbt.model.Bid;
import com.sbt.model.Item;
import com.sbt.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

/**
 * Created by Someone on 18.02.2017.
 */
public class Main {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void main(String args[]) {
        configureSessionFactory();
        Session session = null;
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            //tx = session.beginTransaction();
            User user1 = new User();
            user1.setName("name1");
            User user2 = new User();
            user2.setName("name2");



            Item item1 = new Item();
            item1.setName("ItemName1");
            item1.setUser(user1);

            Item item2 = new Item();
            item2.setName("ItemName2");
            item2.setUser(user1);

            Item item3 = new Item();
            item3.setName("ItemName3");
            item3.setUser(user2);


            Bid bid1 = new Bid();
            bid1.setUser(user1);
            bid1.setItem(item3);

            Bid bid2 = new Bid();
            bid2.setUser(user2);
            bid2.setItem(item1);


            session.persist(user1);
            session.persist(user2);
            session.persist(item1);
            session.persist(item2);
            session.persist(item3);

            session.persist(bid1);
            session.persist(bid2);

            tx = session.beginTransaction();

            List<User> list = session.createCriteria(User.class).list();
            List<Bid> listBid = session.createCriteria(Bid.class).list();
            List<Item> listOfItems = session.createCriteria(Item.class).list();

            for (User userItem : list) {
                System.out.println(userItem.getName());
            }

            for (Item item : listOfItems) {
                System.out.println(item.getUser().getName() + " " + item.getName());
            }

            for (Bid bid : listBid) {
                System.out.println(bid.getUser().getName() + " " + bid.getItem().getName());
            }

            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
