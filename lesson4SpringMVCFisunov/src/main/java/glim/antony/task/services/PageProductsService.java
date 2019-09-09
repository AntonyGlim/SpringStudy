package glim.antony.task.services;

import glim.antony.task.entities.Product;
import glim.antony.task.repositories.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageProductsService {
    PageRepository pageRepository;

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Page<Product> findAllWithPaging(Integer pageNumber){
        return pageRepository.findAll(PageRequest.of(pageNumber, 5, Sort.by(Sort.Direction.ASC, "cost")));
    }
}
