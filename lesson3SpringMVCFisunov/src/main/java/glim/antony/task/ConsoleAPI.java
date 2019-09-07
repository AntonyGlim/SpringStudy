package glim.antony.task;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

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

    public static SessionFactory factory = new Configuration()
            .configure("consoleapi.cfg.xml")
            .buildSessionFactory();

    public static Session session = null;

    public static void main(String[] args) {

        try {
            showProductsList(1L);
            showClientsList(2L);
            deleteProductById(1L);

        } finally {
            factory.close();
            session.close();
        }
    }

    /**Метод удалит продукт по id*/
    private static Product deleteProductById(Long id){
        Product product = null;
        session = factory.getCurrentSession();
        session.beginTransaction();
        product = session.get(Product.class, id);
        try {
            session.delete(session.get(Product.class, id));
        } catch (IllegalArgumentException e){
            System.out.println("No such element in DB!");
        }
        session.getTransaction().commit();
        System.out.println("Deleted product: " + product);
        return product;
    }

    /**Метод выведет информацию о всех покупках пользователя*/
    private static void showProductsList(Long clientId){
        session = factory.getCurrentSession();
        session.beginTransaction();
        Client client = session.get(Client.class, clientId);
        System.out.println(client);
        System.out.println("Products: ");
        for (Product p : client.getProducts()) {
            System.out.println(p.getTitle());
        }
    }

    /**Метод выведет информацию о всех пользователях, которые купили товар*/
    private static void showClientsList(Long productId){
        Product product = session.get(Product.class, productId);
        System.out.println(product);
        System.out.println("Clients: ");
        for (Client c : product.getClients()) {
            System.out.println(c.getName());
        }
        session.getTransaction().commit();
    }
}
