package glim.antony.services;

import glim.antony.entities.Product;
import glim.antony.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(Product product){
        productRepository.add(product);
    }

    public Product getProductByID(long id){
        return productRepository.getProductByID(id);
    }

    public List<Product> getProductList(){
        return productRepository.getProductList();
    }
}
