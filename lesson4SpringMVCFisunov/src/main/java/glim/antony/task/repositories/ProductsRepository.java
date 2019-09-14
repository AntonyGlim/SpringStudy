package glim.antony.task.repositories;

import glim.antony.task.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByCostBetween(Integer minCost, Integer maxCost);
    Product findByCost(Integer cost);
}
