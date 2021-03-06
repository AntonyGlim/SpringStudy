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

    public List<Product> findAllWithPaging(Integer pageNumber, Integer pageSize){
        return pageRepository.findAllBy(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "cost")));
    }
}
