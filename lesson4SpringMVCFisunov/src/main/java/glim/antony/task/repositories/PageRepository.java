package glim.antony.task.repositories;

import glim.antony.task.entities.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends PagingAndSortingRepository<Product, Integer> {

    List<Product> findAllBy(Pageable pageable);
}
