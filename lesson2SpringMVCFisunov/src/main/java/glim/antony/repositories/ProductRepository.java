package glim.antony.repositories;

import glim.antony.entities.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public void add(Product product){
        productList.add(product);
    }

    public void remove(Product product){
        productList.remove(product);
    }

    public List<Product> getProductList(){
        return productList;
    }

    public Product getProductByID(long id){
        for (Product product : productList) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }
}
