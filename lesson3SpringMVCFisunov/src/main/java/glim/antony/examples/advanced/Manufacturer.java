package glim.antony.examples.advanced;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @Column(name = "id")
    @GeneratedValue
    Long id;

    @Column(name = "title")
    String title;

    @OneToMany(mappedBy = "manufacturer")
    List<Product> products;

    @Formula("(SELECT avg(p.price) FROM products p WHERE p.manufacturer_id = id)")
    BigDecimal avgProductsPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Manufacturer(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Manufacturer() {
    }

    @Override
    public String toString() {
        return String.format("Manufacturer [ id = %d, title = %s ]", id, title);
    }
}
