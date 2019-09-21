in project https://github.com/AntonyGlim/SpringLEDMarket
﻿Не работает правильная посылка исключения.

Если захотеть получить продукт с не правильным id, то правильный JSON не прилетит

Alexander Fisunov, [18.09.19 19:22]
правильно
Alexander Fisunov, [18.09.19 19:22]
return productsRepository.findById(id).get(); нулл не вернет


```
-------------------------ProductsRestController.java-------------------------

package glim.antony.spring_led_market.controllers;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.errors_hendlers.ResourceNotFoundException;
import glim.antony.spring_led_market.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/v1/products")
public class ProductsRestController {

    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/") //http://localhost:8189/market/rest/v1/products/
    @ResponseStatus(HttpStatus.OK)
    public List<Product> showProducts(){
        return productsService.findAll();
    }

    @GetMapping("/{id}") //http://localhost:8189/market/rest/v1/products/2
    @ResponseStatus(HttpStatus.OK)
    public Product showProductById(@PathVariable(name = "id") Long id){
        Product product = productsService.findById(id);
        if (product == null) throw new ResourceNotFoundException("Product not found. (id = " + id + ")");
        return product;
    }

    @PostMapping("/") //http://localhost:8189/market/rest/v1/products/
    @ResponseStatus(HttpStatus.CREATED)
    public Product addNewProduct(@RequestBody Product product){
        product.setId(null); //защита
        return productsService.save(product);
    }

    @PutMapping("/")
    public Product updateProduct(@RequestBody Product product){
//        if (product.getId() == null) throw new ...Exception("Product have no id"); //защита
        return productsService.save(product);
    }

    @PutMapping("/{id}")
    public Product saveOrUpdate(@RequestBody Product newProduct, @PathVariable Long id){
        Product product = productsService.findById(id);
        if (product != null){
            product.setTitle(newProduct.getTitle());
            product.setCost(newProduct.getCost());
        } else {
            product = newProduct;
            product.setId(id);
        }
        return productsService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productsService.deleteById(id);
    }
}
```  
  
```
------------------------- ErrorResponse.java -------------------------
package glim.antony.spring_led_market.errors_hendlers;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}

```  
  
```
------------------------- ResourceNotFoundException.java -------------------------
package glim.antony.spring_led_market.errors_hendlers;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
```  
  
```
------------------------- RestExceptionHandler.java -------------------------
package glim.antony.spring_led_market.errors_hendlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRNFException(ResourceNotFoundException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
```  
  

```

------------------------- ProductsRepository.java -------------------------
  
package glim.antony.spring_led_market.repositories;

import glim.antony.spring_led_market.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductsRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
```  
  
```
------------------------- ProductSpecifications.java -------------------------
  
package glim.antony.spring_led_market.repositories.specifications;

import glim.antony.spring_led_market.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> titleContains(String word) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + word + "%");
    }

    public static Specification<Product> priceGreaterThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), value);
        };
    }

    public static Specification<Product> priceLesserThanOrEq(double value) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.lessThanOrEqualTo(root.get("cost"), value);
        };
    }
}
```  
  
```
------------------------- ProductsService.java -------------------------
package glim.antony.spring_led_market.services;

import glim.antony.spring_led_market.entities.Product;
import glim.antony.spring_led_market.repositories.ProductsRepository;
import glim.antony.spring_led_market.repositories.specifications.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductsService {

    ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Page<Product> findAllByPaginAndFiltering(Specification<Product>  specification, Pageable pageable){
        return productsRepository.findAll(specification, pageable);
    }

    public Product save(Product product){
        return productsRepository.save(product);
    }

    public Product findById(Long id){
        return productsRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<Product> findAll() {
        List<Product> list = new ArrayList();
        productsRepository.findAll().forEach(list::add);
        return list;
    }
}
```  
