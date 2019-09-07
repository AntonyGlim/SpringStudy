package glim.antony.task.services;


import glim.antony.task.entities.Product;
import glim.antony.task.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setEmployeesRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Product> findAll() {
        return (List<Product>)productsRepository.findAll();
    }

    public Product findByCost(Integer cost){
        return productsRepository.findByCost(cost);
    }
}
