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
    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("consoleapi.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Client client = session.get(Client.class, 1L);
            System.out.println(client);
            System.out.println("Products: ");
            for (Product p : client.getProducts()) {
                System.out.println(p.getTitle());
            }
            session.getTransaction().commit();
//            Scanner scanner = new Scanner(System.in);
//            int operationNumber = 0;
//            do {
//                System.out.println("Введите номер операции или 0 для выхода");
//                operationNumber = scanner.nextInt();
//                switch (operationNumber){
//                    case 1:
//                        break;
//                    case 2:
//
//                }
//            } while (operationNumber == 0);

        } finally {
            factory.close();
            session.close();
        }
    }
}
