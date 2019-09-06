package glim.antony.task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. В базе данных (Postgrese) необходимо реализовать возможность
 * хранить информацию о покупателях (id, имя) и товарах (id, название, стоимость).
 * У каждого покупателя свой набор купленных товаров.
 *
 * Задача: написать тестовое консольное приложение,
 * которое позволит посмотреть, какие товары покупал клиент,
 * какие клиенты купили определенный товар, и предоставит возможность удалять из базы товары/покупателей.
 *
 * 2. * Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.
 */
public class ConsoleAPI {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.consoleapi.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {
//            session = factory.getCurrentSession();
//
//            Product milk = new Product(); milk.setTitle("milk"); milk.setCost(40);
//            Product bread = new Product(); bread.setTitle("bread"); bread.setCost(20);
//            Product cheese = new Product(); cheese.setTitle("cheese"); cheese.setCost(90);
//
//            List<Product> productList = new ArrayList<>();
//            productList.add(milk);
//            productList.add(bread);
//            productList.add(cheese);
//
//            Client bob = new Client(); bob.setName("Bob"); bob.setProducts(productList);
//            for (Product product : productList) {
//                product.setClient(bob);
//            }
//
//            session.beginTransaction();
//            session.save(milk);
//            session.save(bread);
//            session.save(cheese);
//            session.save(bob);
//            session.getTransaction().commit();
            

        } finally {
            factory.close();
            session.close();
        }
    }
}
