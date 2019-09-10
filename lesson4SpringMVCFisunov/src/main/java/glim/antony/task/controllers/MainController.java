package glim.antony.task.controllers;

import glim.antony.task.entities.Product;
import glim.antony.task.repositories.ProductsRepository;
import glim.antony.task.services.PageProductsService;
import glim.antony.task.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    private ProductsService productsService;
    private PageProductsService pageProductsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Autowired
    public void setPageProductsService(PageProductsService pageProductsService) {
        this.pageProductsService = pageProductsService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/products")
    public String showProductsPage(Model model) {
        List<Product> productsList = productsService.findAll();
        model.addAttribute("products", productsList);
        return "products";
    }

    @GetMapping("/minmaxfilter")
    public String showProductsWithFilterByMinMaxCost(
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            Model model
    ){
        if (minPrice == null){
            minPrice = 0;
        }
        if (maxPrice == null){
            maxPrice = Integer.MAX_VALUE;
        }
        List<Product> productsList = productsService.findAllByCostBetween(minPrice, maxPrice);
        model.addAttribute("products", productsList);
        return "products";
    }

    @GetMapping("/products/find")
    public String showProductsOnPages(Model model){
        List<Product> productsList = pageProductsService.findAllWithPaging(5);
        model.addAttribute("products", productsList);
        return "products";
    }

}
