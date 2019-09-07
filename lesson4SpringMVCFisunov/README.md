**Задание**
 1. Создать сущность «товар» (id, название, стоимость) и соответствующую таблицу в БД. Заполнить таблицу тестовыми данными (20 записей).
 2. Сделать страницу, в которую будут выведены эти записи.
 3. С помощью GET-запроса указывать фильтрацию по:  
 3.1 только минимальной,  
 3.2 только максимальной,  
 3.3 или минимальной и максимальной цене.
 4. *Добавить постраничное отображение (по 5 записей на странице).
   
 **Решение**
 1. Сконфигурирована таблица:  
 
```
CREATE TABLE products 
(id bigserial, title varchar(255), cost integer, 
PRIMARY KEY (id)); 
 id |   title   | cost 
----+-----------+------
  1 | milk      |   32
  2 | bread     |   18
  3 | cheese    |   99
  4 | tomato    |   47
  5 | lime      |   32
  6 | butter    |   87
  7 | apple     |   98
  8 | asparagus |   18
  9 | eggs      |   60
 10 | banana    |   44
 11 | beef      |   55
 12 | beet      |   78
 13 | berry     |  200
 14 | biscuits  |  120
 15 | bream     |  150
 16 | cabbage   |   60
 17 | cake      |   35
 18 | carrot    |   78
 19 | cherry    |  250
 20 | sold      |   12
(20 rows)
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