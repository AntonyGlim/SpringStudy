package glim.antony.examples.basic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MainClass {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Session session = null;

        try {
            session = factory.getCurrentSession();
            SimpleItem simpleItem = new SimpleItem();
            simpleItem.setCost(100);
            simpleItem.setTitle("Java");

            session.beginTransaction();
            session.save(simpleItem);
            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Reader reader = session.get(Reader.class, 1);
//            Book book = session.get(Book.class, 2);
//            book = session.createQuery("from Book b where b.id = :id", Book.class).setParameter("id", 1).getSingleResult();
//            reader.getBooks().add(book);


//            Author person = (Author) session.createQuery("from Person p where p.name = :title").setParameter("title", "Java").getSingleResult();
// from Products p INNER JOIN p.productlanguages pl
//   where pl.languages.shortname ='eng'
//            List<Book> books = session.createQuery("from Book b JOIN b.author a WHERE b.id = a.id", Book.class).getResultList();
//            System.out.println(books);
//            reader.getBooks().clear();
//            session.getTransaction().commit();
//            CREATE
//            session = factory.getCurrentSession();
//            Catalog catalog = new Catalog("Fantasy #15");
//            session.beginTransaction();
//            session.save(catalog);
//            session.getTransaction().commit();

//            READ
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book harryPotterBook = session.get(Book.class, 1);
//            session.getTransaction().commit();
//            System.out.println(harryPotterBook);


//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Catalog catalog2 = session.find(Catalog.class, 2L);
//
//
//            session.getTransaction().commit();
//
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            catalog2 = session.find(Catalog.class, 2L);
//            session.getTransaction().commit();
//
//            System.out.println(catalog2);

//            UPDATE
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book bookJava1 = session.get(Book.class, 3);
//            bookJava1.setTitle("Java 1 Advanced");
//            session.getTransaction().commit();
//            System.out.println(bookJava1);

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Catalog catalog = session.get(Catalog.class, 1L);
//            catalog.setTitle("Fantasy #8");
//            session.getTransaction().commit();
//            System.out.println(catalog);

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book bookJava1 = session.get(Book.class, 4);
//            session.delete(bookJava1);
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//             List<Book> allBooks = session.createQuery("from Book").getResultList();
////             from Book b where b.title = 'Harry Potter' or b.authorName = 'Rowling'
////             from Book b where b.title LIKE 'Harry%'
////             from Book b where b.title = :title
//            List<Book> allBooks = session.createQuery("from Book b where b.title = :title").setParameter("title", "Java 1").getResultList();
//            System.out.println(allBooks);
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            session.createQuery("update Book set title = 'A'").executeUpdate();
//            session.createQuery("delete from Book where id = 3").executeUpdate();
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Book book = session.get(Book.class, 1);
//            System.out.println(book);
//            session.getTransaction().commit();
//
//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Author author = session.get(Author.class, 1);
//            System.out.println(author);
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            List<Reader> readers = session.createQuery("from Reader").getResultList();
//            System.out.println(readers);
//            session.getTransaction().commit();

//            session = factory.getCurrentSession();
//            session.beginTransaction();
//            Author author = session.get(Author.class, 2);
//            session.delete(author);
//            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
    }
}
