package glim.antony.task.repositories;

import glim.antony.task.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends CrudRepository<Product, Long> {
//    Product findOneByTitle(String title);
    List<Product> findAllByIdBetween(Long minId, Long maxId);
//    List<Product> findAllByCostBetween(int minCost, int maxCost);
}
