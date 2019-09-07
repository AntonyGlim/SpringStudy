**Задание**
 1. В базе данных (Postgrese) необходимо реализовать возможность хранить информацию о покупателях (id, имя) и товарах (id, название, стоимость). У каждого покупателя свой набор купленных товаров.  
 **Задача:** написать тестовое консольное приложение, которое позволит посмотреть, какие товары покупал клиент, какие клиенты купили определенный товар, и предоставит возможность удалять из базы товары/покупателей.
 2. *Добавить детализацию по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом.  
   
 **Решение**
 1. Сконфигурированы 3 таблицы:  
 
```$xslt
Table clients
CREATE TABLE clients 
(id bigserial, name varchar(255), PRIMARY KEY (id));
id | name  
----+-------
  1 | Bob
  2 | Sem
  3 | Karl
  4 | Kenny
  5 | Kitty
```
  
```$xslt
Table products
CREATE TABLE products 
(id bigserial, title varchar(255), cost integer, 
PRIMARY KEY (id));
  id | title  | cost 
----+--------+------
  1 | milk   |   32
  2 | bread  |   18
  3 | cheese |   99
  4 | tomato |   47
  5 | lime   |   32
  6 | butter |   87
  7 | sold   |   12
```
  
```$xslt
Table clients_products
CREATE TABLE clients_products 
(client_id bigint, product_id bigint, 
FOREIGN KEY (client_id) REFERENCES clients (id), 
FOREIGN KEY (product_id) REFERENCES products (id));
 client_id | product_id 
-----------+------------
         1 |          1
         2 |          1
         3 |          3
         4 |          1
         5 |          4
         1 |          5
         2 |          6
         3 |          6
         3 |          7
         4 |          7
         4 |          2
         1 |          2
```
**Пример класса сущности**  
```
@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = -1660938414591635063L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Integer cost;

    @ManyToMany
    @JoinTable(
            name = "clients_products",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clients;

    /* ...getters, setters, constructors... */

    @Override
    public String toString() {
        return String.format("Product [id = %d, title = %s, cost = %d]", id, title, cost);
    }
}
```
**Методы:**  
```
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
```
```
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
```
```
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
```